package com.algebra.githubreposearch20.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.dialog.CustomDialog
import com.algebra.githubreposearch20.presentation.ui.dialog.DialogFilter
import com.algebra.githubreposearch20.presentation.ui.fragments.ReposFragment
import com.bumptech.glide.Glide

fun displayPic(view: View, pictureURL: String, imageView: ImageView) {
    Glide.with(view)
        .load(pictureURL)
        .skipMemoryCache(false)
        .placeholder(R.drawable.imagenotavaliable)
        .error(R.drawable.imagenotavaliable)
        .into(imageView)
}

fun displayMessage(message: String, context: Context) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun displayProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
}

fun hideProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.GONE
}

fun redirectToGitHub(path: String, activity: AppCompatActivity) {
    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    intent.addCategory(Intent.CATEGORY_BROWSABLE)
    intent.data = Uri.parse(path)
    activity.startActivity(intent)
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