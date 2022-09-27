package com.example.gravity_project.network.webview

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewService() :
    WebViewClient() {
    @SuppressLint("NewApi")
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url!!.toString())
        return true
    }


}