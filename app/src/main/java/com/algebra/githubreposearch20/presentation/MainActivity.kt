package com.algebra.githubreposearch20.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.ActivityMainBinding
import com.algebra.githubreposearch20.presentation.ui.dialog.CustomDialogListener

class MainActivity : AppCompatActivity(), CustomDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setTheme(R.style.Theme_GithubRepoSearch20)

        setToolbar()
        setNavHost()

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setToolbar() {
        (this as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun setNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
    }

    override fun okPress() {
        finishAffinity()
    }
}
