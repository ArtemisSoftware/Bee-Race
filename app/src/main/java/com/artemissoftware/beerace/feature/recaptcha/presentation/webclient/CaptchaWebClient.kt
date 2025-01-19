package com.artemissoftware.beerace.feature.recaptcha.presentation.webclient

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.artemissoftware.beerace.feature.recaptcha.presentation.util.CaptchaConstants.URL_VERIFY_CAPTCHA_SUCCESS

class CaptchaWebClient(
    private val onCaptchaSolved: () -> Unit
): WebViewClient() {

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        request?.url?.toString()?.let { url ->

            if (url.contains(URL_VERIFY_CAPTCHA_SUCCESS)) {
                onCaptchaSolved()
            }
        }
        return super.shouldInterceptRequest(view, request)
    }
}