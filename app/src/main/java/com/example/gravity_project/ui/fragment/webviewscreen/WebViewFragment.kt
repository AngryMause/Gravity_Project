package com.example.gravity_project.ui.fragment.webviewscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.gravity_project.databinding.FragmentWebViewBinding
import com.example.gravity_project.localdata.SharedPreferenceService
import com.example.gravity_project.network.webview.WebViewService
import com.example.gravity_project.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>(FragmentWebViewBinding::inflate) {

    companion object {
        fun newInstance(): WebViewFragment {
            return WebViewFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.myWebView.settings.javaScriptEnabled = true
        binding.myWebView.webViewClient = WebViewService(SharedPreferenceService(requireContext()))
        val url = sharedPreferenceService.readFromSharedUrl("https://www.reddit.com/")
        binding.myWebView.loadUrl(url)
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