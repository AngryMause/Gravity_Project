package com.example.gravity_project.network.webview

import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import androidx.core.view.isVisible

class WebChromeService(private val webView: View, private val frameLayout: FrameLayout) :
    WebChromeClient() {
    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)
        webView.isVisible = false
        frameLayout.isVisible = true
        frameLayout.addView(view)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
        webView.isVisible = true
        frameLayout.isVisible = false
    }
}