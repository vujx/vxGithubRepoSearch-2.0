package com.algebra.githubreposearch20.presentation.ui.fragments

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import com.algebra.githubreposearch20.BR
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.util.Constants
import com.bumptech.glide.Glide

class Helper(
    private val activity: AppCompatActivity,
    private val repo: GitHubRepo,
    private val view: View? = null
) : BaseObservable() {

    private var isLoading = true

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
        Log.d("jelude-", view.toString())
        val action = ReposFragmentDirections.actionReposFragmentToRepoDetailsFragment(repo)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
    }

    @Bindable
    fun isLoading() = isLoading

    fun setLoading(loading: Boolean) {
        isLoading = loading
        notifyPropertyChanged(BR.loading)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(
            view: ImageView,
            url: String
        ) {
            Glide.with(view.context)
                .load(url)
                .skipMemoryCache(false)
                .placeholder(R.drawable.imagenotavaliable)
                .error(R.drawable.imagenotavaliable)
                .into(view)
        }
    }
}
