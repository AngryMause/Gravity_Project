package com.example.gravity_project.ui.fragment.webviewscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.example.gravity_project.databinding.FragmentWebViewBinding
import com.example.gravity_project.network.webview.WebChromeService
import com.example.gravity_project.network.webview.WebViewService
import com.example.gravity_project.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.myWebView.settings.javaScriptEnabled = true
        binding.myWebView.webViewClient = WebViewService()
        binding.myWebView.webChromeClient =
            WebChromeService(binding.myWebView, binding.fullScreensVideo)
        binding.myWebView.loadUrl(arguments?.getString("key")!!)
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
                        activity?.finish()
                    }
                }
            })
    }


}