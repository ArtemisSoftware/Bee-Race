package com.artemissoftware.beerace.feature.race.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.artemissoftware.beerace.core.domain.Resource
import com.artemissoftware.beerace.fakes.FakeBeeceptorApi
import com.artemissoftware.beerace.feature.race.data.network.source.BeeceptorApiSource
import com.artemissoftware.beerace.feature.race.domain.models.RaceOverview
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.feature.race.domain.repository.RaceRepository
import com.artemissoftware.beerace.testdata.TestData.raceOverview
import com.artemissoftware.beerace.testdata.TestData.racers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RaceRepositoryImplTest {

    private lateinit var fakeBeeceptorApi: FakeBeeceptorApi
    private lateinit var beeceptorApiSource: BeeceptorApiSource
    private lateinit var raceRepository: RaceRepository

    @BeforeEach
    fun setUp() {
        fakeBeeceptorApi = FakeBeeceptorApi()
        beeceptorApiSource = BeeceptorApiSource(fakeBeeceptorApi)
        raceRepository = RaceRepositoryImpl(beeceptorApiSource = beeceptorApiSource)
    }

    @Test
    fun `Get RaceOverview should return success when API calls succeed`() = runBlocking {

        val result = raceRepository.getRaceOverview()


        assertThat(actual = result is Resource.Success<RaceOverview>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .isEqualTo(raceOverview)
    }

    @Test
    fun `Get RaceOverview should return failure when API calls fail`() = runTest {

        fakeBeeceptorApi.shouldThrowError = true


        val result = raceRepository.getRaceOverview()

        assertThat(actual = result is Resource.Failure)
            .isTrue()
    }

    @Test
    fun `Get RacersPosition should return list of racers when API succeeds`() = runTest {

        val result = raceRepository.getRacersPosition()

        assertThat(actual = result is Resource.Success<List<Racer>>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .isEqualTo(racers)
    }

    @Test
    fun `Get RacersPosition should return failure when API fails`() = runTest {

        fakeBeeceptorApi.shouldThrowError = true

        val result = raceRepository.getRacersPosition()

        assertThat(actual = result is Resource.Failure)
            .isTrue()
    }
}