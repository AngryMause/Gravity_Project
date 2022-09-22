package com.example.gravity_project.ui.fragment.loadingscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gravity_project.R
import com.example.gravity_project.databinding.FragmentLoadingBinding
import com.example.gravity_project.ui.fragment.BaseFragment
import com.example.gravity_project.ui.fragment.webviewscreen.WebViewFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

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
        checkIsFirstSignIn()
    }


    private fun checkIsFirstSignIn() {
        val chek = sharedPreferenceService.readFromSharedIsSign(false)
        if (!chek) {
            sharedPreferenceService.writeToShareIsSignIn(false)
            showWebViewFragmentWithHomeUrl()

        } else {
            showWebViewFragmentWithLinkUrl()
//            sharedPreferenceService.writeToShareIsSignIn(false)
        }
    }


    private fun showWebViewFragmentWithHomeUrl() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            loadingScreenViewModel.response.observe(viewLifecycleOwner) {
                Log.d("Response", it.link)
                replaceFragment(WebViewFragment.newInstance(it.home))
            }
        }
    }

    private fun showWebViewFragmentWithLinkUrl() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            loadingScreenViewModel.response.observe(viewLifecycleOwner) {
                Log.d("Response", it.link)
                replaceFragment(WebViewFragment.newInstance(it.link))
            }
        }
    }


}
