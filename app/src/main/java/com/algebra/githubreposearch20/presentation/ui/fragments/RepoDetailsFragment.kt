package com.algebra.githubreposearch20.presentation.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.FragmentRepoDetailsBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.viewmodel.UserViewModel
import com.algebra.githubreposearch20.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailsFragment : Fragment(R.layout.fragment_repo_details) {

    private lateinit var binding: FragmentRepoDetailsBinding
    private val viewModelUser: UserViewModel by viewModel()

    private val args: RepoDetailsFragmentArgs by navArgs()
    private lateinit var repo: GitHubRepo

    private lateinit var helper: Helper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_details, null, false)

        repo = args.currentRepo
        helper = Helper(activity as MainActivity, repo)
        binding.repo = repo
        binding.helper = helper

        val trans =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = trans

        viewModelUser.getUser(repo.author)
        bind()

        return binding.root
    }

    private fun bind() {
        viewModelUser.user.observe(
            viewLifecycleOwner,
            { result ->
                when (result) {
                    is Resource.Success -> {
                        helper.setLoading(false)
                        binding.user = result.value
                    }
                    is Resource.Failure -> {
                        helper.setLoading(false)
                        displayMessage(result.message, requireContext())
                    }
                    is Resource.Loading -> helper.setLoading(true)
                    else -> {
                    }
                }
            }
        )
    }
}
