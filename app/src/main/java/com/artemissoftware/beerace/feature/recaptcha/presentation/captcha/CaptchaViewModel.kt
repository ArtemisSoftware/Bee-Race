package com.artemissoftware.beerace.feature.recaptcha.presentation.captcha

import androidx.lifecycle.ViewModel
import com.artemissoftware.beerace.feature.race.presentation.tournament.TournamentEvent
import com.artemissoftware.beerace.feature.race.presentation.tournament.model.RaceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CaptchaViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    fun onTriggerEvent(event: CaptchaEvent){
        when(event){
            CaptchaEvent.CaptchaSolved -> markCaptchaSolved()
        }
    }

    private fun markCaptchaSolved() {
        _state.value = true
    }
}