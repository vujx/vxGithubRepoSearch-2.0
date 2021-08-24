package com.algebra.githubreposearch20.presentation.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.FragmentRepoDetailsBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.domain.model.User
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.viewmodel.UserViewModel
import com.algebra.githubreposearch20.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailsFragment : Fragment(R.layout.fragment_repo_details) {

    private val binding by viewBinding(FragmentRepoDetailsBinding::bind)
    private val viewModelUser: UserViewModel by viewModel()

    private val args: RepoDetailsFragmentArgs by navArgs()
    private lateinit var repo: GitHubRepo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trans =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = trans

        repo = args.currentRepo
        viewModelUser.getUser(repo.author)

        bind()
        clickListener()
    }

    private fun bind() {
        viewModelUser.user.observe(
            viewLifecycleOwner, { result ->
                when (result) {
                    is Resource.Success -> {
                        hideProgressBar(binding.progressBar)
                        displayDetails(result.value)
                    }
                    is Resource.Failure -> {
                        hideProgressBar(binding.progressBar)
                        displayMessage(result.message, requireContext())
                    }
                    is Resource.Loading -> displayProgressBar(binding.progressBar)
                    else -> {
                    }
                }

            }
        )
    }

    private fun displayDetails(user: User) {
        binding.apply {
            view?.let { displayPic(it, repo.thumbnailImage, imAuthor) }
            tvAuthor.text = repo.author
            tvDateCreation.text = String.format(getString(R.string.date_create), repo.dateCreation)
            tvDateLastModification.text =
                String.format(getString(R.string.last_modified), repo.lastModification)
            tvProgLang.text = String.format(getString(R.string.prog_lang), repo.language)
            tvRepoName.text = repo.repoName
            tvRepoDesc.text = repo.descriptionRepo
            tvInformationAuthor.text = user.desc
        }
    }

    private fun clickListener() {
        binding.imAuthor.setOnClickListener {
            redirectToGitHub("${Constants.BASE_URL_USER}${repo.author}", activity as MainActivity)
        }

        binding.tvRepoName.setOnClickListener {
            redirectToGitHub(
                "${Constants.BASE_URL_USER}${repo.author}/${repo.repoName}",
                activity as MainActivity
            )
        }
    }
}
