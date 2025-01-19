package com.artemissoftware.beerace.core.designsystem.composables.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.composables.shape
import com.artemissoftware.beerace.core.designsystem.spacing
import com.artemissoftware.beerace.core.designsystem.Black10

@Composable
fun BRButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        shape = MaterialTheme.shape.noCorners,
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Black10
        ),
        content = {
            Text(
                text = text,
                modifier = Modifier.padding(MaterialTheme.spacing.spacing1)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun StartScreenPreview() {
    BeeRaceTheme {
        BRButton(
            text = "Race button",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}