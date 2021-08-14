package com.algebra.githubreposearch20.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.FragmentReposBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.presentation.ui.adapter.RepoAdapter
import com.algebra.githubreposearch20.presentation.ui.adapter.RepoAdapterListener
import com.algebra.githubreposearch20.presentation.ui.viewmodel.GitHubRepoViewModel
import com.algebra.githubreposearch20.util.*
import com.readystatesoftware.chuck.internal.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment(R.layout.fragment_repos), RepoAdapterListener {

    private val binding by viewBinding(FragmentReposBinding::bind)
    private lateinit var searchView: SearchView

    private val viewModelRepo: GitHubRepoViewModel by viewModel()
    private val adapter = RepoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelRepo.getAllGitHubRepos("calc")
        bind()
        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        Log.d("ispisovo", "sasda")
        menuInflater.inflate(R.menu.menu_repos_fragment, menu)
        val searchItem = menu.findItem(R.id.searchIcon)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.searchQuery)

        Log.d("ispis ovo", "sasa")
        searchAction(searchView, requireContext(), viewModelRepo)
        return super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filterIcon -> {
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView() {
        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepos.adapter = adapter
    }

    private fun bind() {
        viewModelRepo.repos.observe(
            viewLifecycleOwner,
            { result ->
                when (result) {
                    is Resource.Success -> {
                        hideProgressBar(binding.progressBar)
                        binding.tvSearchNoResult.text = ""
                        adapter.setList(result.value)
                    }
                    is Resource.Failure -> {
                        hideProgressBar(binding.progressBar)
                        binding.tvSearchNoResult.text = ""
                        displayMessage(result.message, requireContext())
                    }
                    is Resource.Loading -> displayProgressBar(binding.progressBar)
                    is Resource.Empty -> {
                        adapter.setList(emptyList())
                        binding.tvSearchNoResult.text = getString(R.string.repo_not_found)
                    }
                }
            }
        )
    }

    override fun onItemClick(gitHubRepo: GitHubRepo) {
        val action = ReposFragmentDirections.actionReposFragmentToRepoDetailsFragment()
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    override fun pictureAuthorClick(author: String) {
        redirectToGitHub("${Constants.BASE_URL_USER}$author", activity as MainActivity)
    }
}
