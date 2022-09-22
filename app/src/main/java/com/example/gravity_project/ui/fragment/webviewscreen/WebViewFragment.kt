package com.example.gravity_project.ui.fragment.webviewscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.gravity_project.databinding.FragmentWebViewBinding
import com.example.gravity_project.localdata.SharedPreferenceService
import com.example.gravity_project.network.webview.WebViewService
import com.example.gravity_project.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>(FragmentWebViewBinding::inflate) {

    companion object {
        fun newInstance(arg: String): WebViewFragment {
            val webView = WebViewFragment()
            val bundle = Bundle()
            bundle.putString("key", arg)
            webView.arguments = bundle
            return webView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
//        if (savedInstanceState != null) {
//            binding.myWebView.restoreState(savedInstanceState)
//        } else binding.myWebView.loadUrl(arguments?.getString("key").toString())
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.myWebView.settings.javaScriptEnabled = true
        binding.myWebView.webViewClient = WebViewService(SharedPreferenceService(requireContext()))
//        val url = sharedPreferenceService.readFromSharedUrl("https://www.reddit.com/")
        val url = arguments?.getString("key")
        Log.d("WebView", url.toString())
        binding.myWebView.webChromeClient = object : WebChromeClient() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources!!)
            }
        }
        binding.myWebView.loadUrl(url.toString())
        binding.myWebView
        govBack()
    }

    private fun govBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.myWebView.canGoBack()) {
                        binding.myWebView.goBack()
                    } else {
                        isEnabled = false
                    }
                }
            })
    }


}