package com.algebra.githubreposearch20.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.data.db.SearchDao
import com.algebra.githubreposearch20.databinding.ActivityMainBinding
import com.algebra.githubreposearch20.presentation.ui.dialog.CustomDialogListener
import com.algebra.githubreposearch20.presentation.ui.helper.ConnectionHelper
import com.algebra.githubreposearch20.util.ConnectionLiveData
import com.algebra.githubreposearch20.util.Constants
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity(), CustomDialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val sharedPref: SharedPreferences by inject()
    private val searchDao: SearchDao by inject()

    private lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var connectionHelper: ConnectionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GithubRepoSearch20)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setDataValues()

        setToolbar()
        setNavHost()

        setupActionBarWithNavController(navController)
        addLastDateOfClearingSearchResults()
    }

    private fun setDataValues() {
        connectionLiveData = ConnectionLiveData(context = this)
        connectionHelper = ConnectionHelper(connectionLiveData, this)
        binding.connection = connectionHelper
        connectionHelper.observeInternetConnection()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setToolbar() {
        (this as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun setNavHost() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
    }

    override fun okPress() {
        finishAffinity()
    }

    private fun compareTwoDates(lastDate: String): Double {
        val now = Calendar.getInstance().timeInMillis
        return (now - lastDate.toLong()).toDouble() / (24 * 60 * 60 * 1000)
    }

    private fun addLastDateOfClearingSearchResults() {
        val lastDateStore = sharedPref.getString(Constants.LAST_DATE, "")
        if (lastDateStore.isNullOrEmpty() || compareTwoDates(lastDateStore) > 6.99) {
            val editor = sharedPref.edit()
            val now = Calendar.getInstance().timeInMillis
            editor.putString(Constants.LAST_DATE, now.toString())
            editor.apply()
            lifecycleScope.launchWhenCreated { searchDao.removeAllSearchRepos() }
        }
    }
}
