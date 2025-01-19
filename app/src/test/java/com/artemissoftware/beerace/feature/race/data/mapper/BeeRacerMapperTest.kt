package com.artemissoftware.beerace.feature.race.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.beerace.testdata.DtoTestData.beeDto1
import com.artemissoftware.beerace.testdata.DtoTestData.durationDto
import com.artemissoftware.beerace.testdata.TestData.raceDuration
import com.artemissoftware.beerace.testdata.TestData.racer1
import org.junit.jupiter.api.Test

class BeeRacerMapperTest {

    @Test
    fun `Map DurationDto to RaceDuration`() {
        assertThat(durationDto.toDuration())
            .isEqualTo(raceDuration)
    }

    @Test
    fun `Map BeeDto to Racer`() {
        assertThat(beeDto1.toRacer())
            .isEqualTo(racer1)
    }
}