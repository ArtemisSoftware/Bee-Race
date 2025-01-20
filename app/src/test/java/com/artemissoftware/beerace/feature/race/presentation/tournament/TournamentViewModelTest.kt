package com.artemissoftware.beerace.feature.race.presentation.tournament

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.beerace.fakes.FakeRaceRepository
import com.artemissoftware.beerace.feature.race.presentation.tournament.model.RaceStatus
import com.artemissoftware.beerace.testdata.TestData
import com.artemissoftware.beerace.util.MainCoroutineExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class TournamentViewModelTest {

    private lateinit var raceRepository: FakeRaceRepository

    private lateinit var viewModel: TournamentViewModel

    @BeforeEach
    fun setUp() {
        raceRepository = FakeRaceRepository()
        viewModel = TournamentViewModel(raceRepository)
    }

    @Test
    fun `startRace - success updates state correctly`() = runTest {

        viewModel.onTriggerEvent(TournamentEvent.ResumeRace)

        val state = viewModel.state.value
        assertThat(TestData.raceOverview.racers).isEqualTo(state.racers)
        assertThat(TestData.raceDuration).isEqualTo(state.duration)
        assertThat(RaceStatus.RUNNING).isEqualTo(state.status)
    }

    @Test
    fun `startRace - captcha error opens captcha screen`() = runTest {

        raceRepository.shouldReturnCaptchaError = true
        val viewModel = TournamentViewModel(raceRepository)

        viewModel.onTriggerEvent(TournamentEvent.ResumeRace)

        advanceUntilIdle()
        val state = viewModel.state.value
        assertThat(RaceStatus.MUST_RESTART_RACE).isEqualTo(state.status)
    }

    @Test
    fun `updateRace - successful update updates racers list`() = runTest {

        viewModel.onTriggerEvent(TournamentEvent.ResumeRace)

        // Then
        val state = viewModel.state.value
        assertThat(TestData.raceOverview.racers).isEqualTo(state.racers)
    }

    @Test
    fun `cancel countdown stops race and clears jobs`() = runTest {

        viewModel.onTriggerEvent(TournamentEvent.ResumeRace)
        viewModel.onTriggerEvent(TournamentEvent.CancelRace)

        viewModel.state.test {
            val emission1 = awaitItem()
            assertThat(emission1.status).isEqualTo(RaceStatus.INTERRUPTED)
        }
    }

    @Test
    fun `resume countdown restarts race correctly`() = runTest {
        viewModel.onTriggerEvent(TournamentEvent.CancelRace)
        viewModel.onTriggerEvent(TournamentEvent.ResumeRace)

        viewModel.state.test {
            val emission1 = awaitItem()
            assertThat(emission1.status).isEqualTo(RaceStatus.INTERRUPTED)

            val emission2 = awaitItem()
            assertThat(emission2.status).isEqualTo(RaceStatus.INTERRUPTED)

            val emission3 = awaitItem()
            assertThat(emission3.status).isEqualTo(RaceStatus.RUNNING)
        }
    }
}