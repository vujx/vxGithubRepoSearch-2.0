package com.algebra.githubreposearch20.presentation.ui.helper

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
<<<<<<< HEAD
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.presentation.ui.fragments.ReposFragmentDirections
import com.algebra.githubreposearch20.util.Constants
import com.algebra.githubreposearch20.util.displayMessage
=======
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.presentation.ui.fragments.ReposFragmentDirections
import com.algebra.githubreposearch20.util.Constants
>>>>>>> develop

class OnClickHelper(
    private val activity: AppCompatActivity,
    private val repo: GitHubRepo,
    private val view: View? = null
) {

    fun redirectToGitHub() {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.data = Uri.parse("${Constants.BASE_URL_USER}${repo.author}")
        activity.startActivity(intent)
    }

    fun redirectToGitHubRepo() {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.data = Uri.parse("${Constants.BASE_URL_USER}${repo.author}/${repo.repoName}")
        activity.startActivity(intent)
    }

    fun onItemClick() {
        val action = ReposFragmentDirections.actionReposFragmentToRepoDetailsFragment(repo)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
    }
<<<<<<< HEAD

    fun onItemClickFree() {
        displayMessage(App.getStringResource(R.string.try_premium_message), activity)
    }
=======
>>>>>>> develop
}
