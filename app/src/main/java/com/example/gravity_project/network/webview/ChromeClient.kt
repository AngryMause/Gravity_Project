package com.example.gravity_project.network.webview

import android.view.View
import android.webkit.WebChromeClient

open class ChromeClient() : WebChromeClient() {

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)
    }


}

