package com.example.gravity_project.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gravity_project.R
import com.example.gravity_project.ui.fragment.loadingscreen.LoadingFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, LoadingFragment.newInstance()).commit()
        }


    }
}