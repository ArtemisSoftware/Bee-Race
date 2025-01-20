package com.artemissoftware.beerace.feature.recaptcha.presentation.captcha

import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.artemissoftware.beerace.feature.recaptcha.presentation.util.CaptchaConstants.URL_VERIFY_CAPTCHA_SUCCESS
import com.artemissoftware.beerace.feature.recaptcha.presentation.webclient.CaptchaWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("JavascriptInterface")
@Composable
fun CaptchaScreen(
    url: String,
    onCaptchaSolved: () -> Unit
) {

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

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
                                coroutine.launch {
                                    onCaptchaSolved()
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}