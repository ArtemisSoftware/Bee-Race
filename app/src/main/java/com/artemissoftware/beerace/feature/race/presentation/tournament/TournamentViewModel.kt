package com.artemissoftware.beerace.feature.race.presentation.tournament

import androidx.lifecycle.viewModelScope
import com.artemissoftware.beerace.core.domain.error.DataError
import com.artemissoftware.beerace.core.presentation.util.UiText
import com.artemissoftware.beerace.core.presentation.util.events.UiEvent
import com.artemissoftware.beerace.core.presentation.util.events.UiEventViewModel
import com.artemissoftware.beerace.core.presentation.util.extension.toUiText
import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.feature.race.domain.repository.RaceRepository
import com.artemissoftware.beerace.feature.race.presentation.navigation.RaceRoute
import com.artemissoftware.beerace.feature.race.presentation.tournament.model.RaceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val raceRepository: RaceRepository
): UiEventViewModel() {

    private val _state = MutableStateFlow(TournamentState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null
    private var updateJob: Job? = null

    init {
        startRace()
    }

    fun onTriggerEvent(event: TournamentEvent){
        when(event){
            TournamentEvent.ResumeRace -> resumeCountdown()
            TournamentEvent.CancelRace -> cancelCountdown(RaceStatus.INTERRUPTED)
        }
    }

    private fun startRace() = with(_state){
        update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            raceRepository.getRaceOverview()
                .onSuccess { overview ->
                    update {
                        it.copy(
                            racers = overview.racers,
                            duration = RaceDuration(overview.raceDuration.timeInSeconds),
                            updateDelay = overview.updateDelay,
                            isLoading = false
                        )
                    }
                    startCountdown()
                }
                .onFailure { error ->
                    update {
                        it.copy(isLoading = false)
                    }

                    when(error){
                        is DataError.NetworkError.CaptchaControl -> openCaptcha(error.captcha.url, RaceStatus.MUST_RESTART_RACE)
                        is DataError.NetworkError.Error -> sendError(UiText.DynamicString(error.message))
                        else -> sendError(error. toUiText())
                    }
                }
        }
    }

    private fun updateRace() = with(_state){
        updateJob = viewModelScope.launch {

            while (value.duration.timeInSeconds > 0) {
                delay(value.updateDelay)

                raceRepository.getRacersPosition()
                    .onSuccess { racers ->
                        update {
                            it.copy(racers = racers)
                        }
                    }
                    .onFailure { error ->

                        when(error){
                            is DataError.NetworkError.CaptchaControl -> openCaptcha(error.captcha.url)
                            is DataError.NetworkError.Error -> sendError(UiText.DynamicString(error.message))
                            else -> sendError(error. toUiText())
                        }
                    }
            }
        }
    }

    private fun startCountdown() = with(_state) {
        if (value.status == RaceStatus.RUNNING) return // Prevent multiple countdowns

        updateRaceStatus(RaceStatus.RUNNING)
        updateRace()
        timerJob = viewModelScope.launch {
            while (value.duration.timeInSeconds > 0) {
                delay(UPDATE_DELAY)

                update {
                    it.copy(duration = RaceDuration(timeInSeconds = value.duration.timeInSeconds - 1))
                }
            }
            finishRace()
        }
    }

    private fun cancelCountdown(status: RaceStatus) {
        updateRaceStatus(status)
        timerJob?.cancel()
        timerJob = null
        updateJob?.cancel()
        updateJob = null
    }

    private fun resumeCountdown() = with(_state.value){
        when(status) {
            RaceStatus.PAUSED -> startCountdown()
            RaceStatus.INTERRUPTED -> startRace()
            RaceStatus.MUST_RESTART_RACE -> startRace()
            RaceStatus.FINISHED -> startRace()
            else -> Unit
        }
    }

    private fun finishRace() = with(_state.value){
        cancelCountdown(RaceStatus.FINISHED)
        if(racers.isEmpty()){
            updateRace()
        } else {
            viewModelScope.launch {
                val racer = racers.first()
                sendUiEvent(
                    UiEvent.NavigateWithRoute(RaceRoute.Winner(color = racer.color, name = racer.name))
                )
            }
        }
    }


    private fun sendError(message: UiText) {
        cancelCountdown(RaceStatus.INTERRUPTED)
        viewModelScope.launch {
            sendUiEvent(UiEvent.Navigate(message, RaceRoute.ERROR))
        }
    }

    private fun openCaptcha(url: String, raceStatus: RaceStatus = RaceStatus.PAUSED) {
        cancelCountdown(raceStatus)
        viewModelScope.launch {
            sendUiEvent(UiEvent.Navigate(url, RaceRoute.CAPTCHA))
        }
    }

    private fun updateRaceStatus(raceStatus: RaceStatus) = with(_state){
        update { it.copy(status = raceStatus) }
    }

    override fun onCleared() {
        super.onCleared()
        cancelCountdown(status = RaceStatus.FINISHED)
    }

    private companion object{
        const val UPDATE_DELAY = 1000L
    }
}