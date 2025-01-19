package com.artemissoftware.beerace.core.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.core.designsystem.composables.button.BRButton
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetry: () -> Unit
) {
    BackHandler {

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium
        )

        Image(
            painter = painterResource(id = R.drawable.ic_broken),
            contentDescription = "Winner Icon",
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium
        )

        Image(
            painter = painterResource(id = R.drawable.ic_run),
            contentDescription = "Winner Icon",
            modifier = Modifier.size(60.dp)
        )

        BRButton(
            text = stringResource(R.string.retry),
            onClick = onRetry
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    BeeRaceTheme {
        ErrorScreen(
            errorMessage = "An error occurred. Please try again.",
            onRetry = {}
        )
    }
}