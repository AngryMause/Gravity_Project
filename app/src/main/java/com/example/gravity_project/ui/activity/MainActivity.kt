package com.example.gravity_project.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gravity_project.R
import com.example.gravity_project.ui.fragment.loadingscreen.LoadingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, LoadingFragment.newInstance()).commit()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.navigationBars())
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        }
    }


}