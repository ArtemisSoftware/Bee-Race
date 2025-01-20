package com.artemissoftware.beerace.core.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.composables.button.BRButton
import com.artemissoftware.beerace.core.designsystem.dimension
import com.artemissoftware.beerace.core.designsystem.spacing

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
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.oops),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing2))

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = errorMessage,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing3))

        Image(
            painter = painterResource(id = R.drawable.ic_broken),
            contentDescription = "Winner Icon",
            modifier = Modifier.size(MaterialTheme.dimension.iconSizeExtraBig)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing3))

        Text(
            modifier = Modifier.fillMaxWidth(0.2F),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.don_t_worry_we_are_on_it),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing1_5))

        Image(
            painter = painterResource(id = R.drawable.ic_run),
            contentDescription = "Winner Icon",
            modifier = Modifier.size(MaterialTheme.dimension.iconSizeSmall)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing1_5))

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