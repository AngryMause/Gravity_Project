package com.example.gravity_project.network.webview

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.gravity_project.localdata.SharedPreferenceService

class WebViewService() :
    WebViewClient() {
    @SuppressLint("NewApi")
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url!!.toString())
        Log.d("WebService", request?.url!!.toString())
        return true
    }


}