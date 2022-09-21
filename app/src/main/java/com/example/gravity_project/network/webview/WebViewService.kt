package com.example.gravity_project.network.webview

import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.gravity_project.localdata.SharedPreferenceService

class WebViewService(private val sharedPreferenceService: SharedPreferenceService) :
    WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        sharedPreferenceService.writeURLToShared(request?.url.toString())
        return true
    }

}