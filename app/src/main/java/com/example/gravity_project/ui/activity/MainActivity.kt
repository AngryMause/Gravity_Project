package com.example.gravity_project.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gravity_project.R
import com.example.gravity_project.ui.fragment.webviewscreen.WebViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, WebViewFragment.newInstance()).commit()
        }
    }
}