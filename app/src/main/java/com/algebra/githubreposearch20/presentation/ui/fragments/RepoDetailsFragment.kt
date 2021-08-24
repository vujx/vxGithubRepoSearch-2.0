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
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.helper.ImageHelper
import com.algebra.githubreposearch20.presentation.ui.helper.OnClickHelper
import com.algebra.githubreposearch20.presentation.ui.helper.ProgressBarHelper
import com.algebra.githubreposearch20.presentation.ui.viewmodel.UserViewModel
import com.algebra.githubreposearch20.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailsFragment : Fragment(R.layout.fragment_repo_details) {

    private lateinit var binding: FragmentRepoDetailsBinding
    private val viewModelUser: UserViewModel by viewModel()

    private val args: RepoDetailsFragmentArgs by navArgs()
    private val progressBarHelper = ProgressBarHelper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_details, null, false)

        setDataValues()
        createAnimation()

        viewModelUser.getUser(args.currentRepo.author)
        bind()

        return binding.root
    }

    private fun setDataValues() {
        binding.apply {
            repo = args.currentRepo
            clickHelper = OnClickHelper(activity as MainActivity, args.currentRepo)
            progressBarHelper = this@RepoDetailsFragment.progressBarHelper
            imageHelper = ImageHelper()
        }
    }

    private fun createAnimation() {
        val trans =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = trans
    }

    private fun bind() {
        viewModelUser.user.observe(
            viewLifecycleOwner,
            { result ->
                when (result) {
                    is Resource.Success -> {
                        progressBarHelper.setLoading(false)
                        binding.user = result.value
                    }
                    is Resource.Failure -> {
                        progressBarHelper.setLoading(false)
                        displayMessage(result.message, requireContext())
                    }
                    is Resource.Loading -> progressBarHelper.setLoading(true)
                    else -> {
                    }
                }
            }
        )
    }
}
