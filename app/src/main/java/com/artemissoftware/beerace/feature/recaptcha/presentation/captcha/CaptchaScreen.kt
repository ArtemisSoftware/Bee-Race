package com.artemissoftware.beerace.feature.recaptcha.presentation.captcha

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.beerace.feature.recaptcha.presentation.webclient.CaptchaWebClient


@SuppressLint("JavascriptInterface")
@Composable
fun CaptchaScreen(
    url: String,
    onCaptchaSolved: () -> Unit,
    onAbandonCaptcha: () -> Unit,
    viewModel: CaptchaViewModel = hiltViewModel()
) {

    BackHandler {
        onAbandonCaptcha()
    }

    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state) {
            onCaptchaSolved()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            AndroidView(
                modifier = Modifier.align(Alignment.Center),
                factory = {
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true

                        loadUrl(url)

                        webViewClient = CaptchaWebClient(
                            onCaptchaSolved = {
                                viewModel.onTriggerEvent(CaptchaEvent.CaptchaSolved)
//                                coroutine.launch {
//                                    onCaptchaSolved()
//                                }
                            }
                        )
                    }
                }
            )
        }
    }
}