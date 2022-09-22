package com.example.gravity_project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.gravity_project.R
import com.example.gravity_project.localdata.SharedPreferenceService
import com.example.gravity_project.ui.fragment.webviewscreen.WebViewFragment

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    protected val sharedPreferenceService by lazy { SharedPreferenceService(requireContext()) }

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }


    protected fun replaceFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.apply {
            beginTransaction().replace(R.id.main_container, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}