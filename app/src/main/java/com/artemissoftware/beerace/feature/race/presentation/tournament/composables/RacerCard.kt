package com.artemissoftware.beerace.feature.race.presentation.tournament.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.core.ui.composables.icon.RacerIcon
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.dimension
import com.artemissoftware.beerace.core.designsystem.spacing
import com.artemissoftware.beerace.core.presentation.util.extension.toColor
import com.artemissoftware.beerace.core.presentation.util.extension.toMedal
import com.artemissoftware.beerace.core.presentation.util.extension.toOrdinal

@Composable
fun RaceCard(
    position: Int,
    racer: Racer,
    modifier: Modifier = Modifier
) {
    Row(
        modifier =modifier
            .padding(MaterialTheme.spacing.spacing2),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RacerIcon(
            backgroundColor = racer.color.toColor()
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
        ) {
            Text(
                text = position.toOrdinal().asString(),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = racer.name,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )
        }

        if(position <= 3){
            Image(
                painter = painterResource(position.toMedal()),
                contentDescription = "Medal Icon",
                modifier = Modifier.size(MaterialTheme.dimension.iconSize)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun RaceCard_with_medal_Preview() {
    BeeRaceTheme {
        RaceCard(
            position = 2,
            racer = Racer(name = "Speed Racer", color = "#8d62a1"),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RaceCard_with_no_medal_Preview() {
    BeeRaceTheme {
        RaceCard(
            position = 5,
            racer = Racer(name = "Speed Racer", color = "#8d62a1"),
            modifier = Modifier.fillMaxWidth()
        )
    }
}