package com.example.gravity_project.ui.fragment.loadingscreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import com.example.gravity_project.databinding.FragmentLoadingBinding
import com.example.gravity_project.ui.fragment.BaseFragment
import com.example.gravity_project.ui.fragment.webviewscreen.WebViewFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingFragment : BaseFragment<FragmentLoadingBinding>(FragmentLoadingBinding::inflate) {
    private val loadingScreenViewModel: LoadingScreenViewModel by viewModels()

    companion object {
        fun newInstance(): LoadingFragment {
            return LoadingFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendUrlToWebViewFragment()
    }

    private fun sendUrlToWebViewFragment() {
        viewLifecycleOwner.lifecycleScope.launch {
            loadingScreenViewModel.response.asFlow().collect { url ->
                if (url.isNotEmpty()) {
                    hideUiElement()
                    replaceFragment(WebViewFragment.newInstance(url))
                } else binding.myProgressBar.isVisible = true
            }
        }
    }

    private fun hideUiElement() {
        binding.myProgressBar.isVisible = false
        binding.loading.isVisible = false
    }


}
