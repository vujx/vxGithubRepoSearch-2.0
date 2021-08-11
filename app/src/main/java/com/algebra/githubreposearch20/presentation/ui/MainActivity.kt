package com.algebra.githubreposearch20.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.ActivityMainBinding
import com.algebra.githubreposearch20.util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
