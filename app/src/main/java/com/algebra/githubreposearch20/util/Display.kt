package com.algebra.githubreposearch20.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.dialog.CustomDialog
import com.algebra.githubreposearch20.presentation.ui.dialog.DialogFilter
import com.algebra.githubreposearch20.presentation.ui.fragments.ReposFragment

fun displayMessage(message: String, context: Context) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun showFilterDialog(requireActivity: FragmentActivity, fragment: ReposFragment) {
    val dialog = DialogFilter(fragment)
    dialog.show(requireActivity.supportFragmentManager, "FilterRepos")
}

fun exitFromApp(activity: MainActivity) {
    activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)?.let {
        if (it.childFragmentManager.fragments[0].toString().contains("ReposFragment")) {
            val dialog = CustomDialog(activity, activity.getString(R.string.exit_app))
            dialog.show(activity.supportFragmentManager, "Logout")
        }
    }
}
