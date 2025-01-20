package com.artemissoftware.beerace.feature.race.presentation.tournament

import androidx.lifecycle.viewModelScope
import com.artemissoftware.beerace.core.domain.error.DataError
import com.artemissoftware.beerace.core.presentation.util.events.UiEvent
import com.artemissoftware.beerace.core.presentation.util.events.UiEventViewModel
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
                            updateDelay = getUpdateDelay(overview.raceDuration.timeInSeconds),
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
                        is DataError.NetworkError.CaptchaControl -> openCaptcha(error.url, RaceStatus.MUST_RESTART_RACE)
                        is DataError.NetworkError.Error -> updateError(error.message)
                        else -> updateError("THe error I did not take care of")// TODO
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
                            is DataError.NetworkError.CaptchaControl -> openCaptcha(error.url)
                            is DataError.NetworkError.Error -> updateError(error.message)
                            else -> updateError("THe error I did not take care of") // TODO
                        }
                    }
            }
        }
    }

    private fun getUpdateDelay(timeInSeconds: Int): Long {
        val allowedCalls = (timeInSeconds * 0.5).toInt().coerceAtMost(30)
        val delay = if (allowedCalls > 0) timeInSeconds * 1000L / allowedCalls else 0L
        return delay
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


    private fun updateError(message: String) {
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

    private companion object{
        const val UPDATE_DELAY = 1000L
    }
}