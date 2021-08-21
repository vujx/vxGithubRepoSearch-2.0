package com.algebra.githubreposearch20.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.FragmentRepoDetailsBinding
import com.algebra.githubreposearch20.util.viewBinding

class RepoDetailsFragment : Fragment(R.layout.fragment_repo_details) {

    private val binding by viewBinding(FragmentRepoDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }
}
