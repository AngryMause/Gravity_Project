package com.example.gravity_project.ui.fragment.webviewscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gravity_project.R
import com.example.gravity_project.ui.fragment.loadingscreen.LoadingFragment

class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    companion object {
        fun newInstance(): LoadingFragment {
            return LoadingFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}