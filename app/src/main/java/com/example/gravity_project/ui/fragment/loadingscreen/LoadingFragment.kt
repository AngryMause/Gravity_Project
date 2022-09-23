package com.example.gravity_project.ui.fragment.loadingscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
        viewLifecycleOwner.lifecycleScope.launch {
            loadingScreenViewModel.response.observe(viewLifecycleOwner) {
                replaceFragment(WebViewFragment.newInstance(it))
            }
        }
    }


}
