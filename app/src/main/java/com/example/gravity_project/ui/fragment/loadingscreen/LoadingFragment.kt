package com.example.gravity_project.ui.fragment.loadingscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gravity_project.R

class LoadingFragment : Fragment(R.layout.fragment_loading) {

    companion object {
        fun newInstance(): LoadingFragment {
            return LoadingFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}