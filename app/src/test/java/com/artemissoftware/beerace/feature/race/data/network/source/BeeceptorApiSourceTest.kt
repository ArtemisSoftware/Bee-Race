package com.artemissoftware.beerace.feature.race.data.network.source

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.beerace.fakes.FakeBeeceptorApi
import com.artemissoftware.beerace.testdata.DtoTestData.raceStatusDto
import com.artemissoftware.beerace.testdata.DtoTestData.timeInSeconds
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BeeceptorApiSourceTest  {

    private lateinit var fakeBeeceptorApi: FakeBeeceptorApi
    private lateinit var beeceptorApiSource: BeeceptorApiSource

    @BeforeEach
    fun setUp() {
        fakeBeeceptorApi = FakeBeeceptorApi()
        beeceptorApiSource = BeeceptorApiSource(fakeBeeceptorApi)
    }

    @Test
    fun `Get duration should return DurationDto`() = runBlocking {

        val result = beeceptorApiSource.getDuration()

        assertThat(timeInSeconds)
            .isEqualTo(result.timeInSeconds)
    }

    @Test
    fun `Get duration should throw error when API fails`() = runTest {

        fakeBeeceptorApi.shouldThrowError = true

        assertFailure {
            beeceptorApiSource.getDuration()
        }
    }

    @Test
    fun `Get RaceStatus should return RaceStatusDto`() = runBlocking {

        val result = beeceptorApiSource.getRaceStatus()

        assertThat(result)
            .isEqualTo(raceStatusDto)
    }

    @Test
    fun `Get RaceStatus should throw error when API fails`() = runTest {

        fakeBeeceptorApi.shouldThrowError = true

        assertFailure {
            beeceptorApiSource.getRaceStatus()
        }
    }
}