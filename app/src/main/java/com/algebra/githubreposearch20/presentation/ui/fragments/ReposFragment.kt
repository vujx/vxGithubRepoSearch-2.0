package com.algebra.githubreposearch20.presentation.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
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
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.adapter.RepoAdapter
import com.algebra.githubreposearch20.presentation.ui.adapter.RepoAdapterListener
import com.algebra.githubreposearch20.presentation.ui.dialog.DialogFilterListener
import com.algebra.githubreposearch20.presentation.ui.viewmodel.GitHubRepoViewModel
import com.algebra.githubreposearch20.util.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment(R.layout.fragment_repos), RepoAdapterListener, DialogFilterListener {

    private val binding by viewBinding(FragmentReposBinding::bind)
    private lateinit var searchView: SearchView

    private val viewModelRepo: GitHubRepoViewModel by viewModel()
    private val adapter = RepoAdapter(this)

    private val sharedPref: SharedPreferences by inject()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLastSearchRepos()
        bind()
        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_repos_fragment, menu)
        val searchItem = menu.findItem(R.id.searchIcon)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.searchQuery)

        searchAction(searchView, requireContext(), viewModelRepo)
        return super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filterIcon -> {
                showFilterDialog(requireActivity(), this@ReposFragment)
                true
            }
            else -> true
        }
    }

    private fun getLastSearchRepos() {
        val listOfRepos = sharedPref.getString(Constants.SEARCH_REPOS, "")
        if (listOfRepos.isNullOrBlank()) {
            hideProgressBar(binding.progressBar)
            binding.tvSearchNoResult.text = getString(R.string.search_message)
        } else {
            hideProgressBar(binding.progressBar)
            adapter.setList(getListOfSearchRepos(listOfRepos))
        }
    }

    private fun getListOfSearchRepos(json: String): List<GitHubRepo> {
        val type = object : TypeToken<java.util.ArrayList<GitHubRepo>?>() {}.type
        return gson.fromJson(json, type)
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
                        val editor = sharedPref.edit()
                        val jsonOfGitHubRepos = gson.toJson(result.value)
                        editor.putString(Constants.SEARCH_REPOS, jsonOfGitHubRepos).apply()
                    }
                    is Resource.Failure -> {
                        hideProgressBar(binding.progressBar)
                        binding.tvSearchNoResult.text = ""
                        displayMessage(result.message, requireContext())
                    }
                    is Resource.Loading -> displayProgressBar(binding.progressBar)
                    is Resource.Empty -> {
                        hideProgressBar(binding.progressBar)
                        adapter.setList(emptyList())
                        binding.tvSearchNoResult.text = getString(R.string.repo_not_found)
                    }
                }
            }
        )
    }

    private fun setUpRecyclerView() {
        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepos.adapter = adapter
    }

    override fun onItemClick(gitHubRepo: GitHubRepo) {
        val action = ReposFragmentDirections.actionReposFragmentToRepoDetailsFragment()
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    override fun pictureAuthorClick(author: String) {
        redirectToGitHub("${Constants.BASE_URL_USER}$author", activity as MainActivity)
    }

    override fun pressFilter(filter: String) {
        when (filter) {
            "Stars" -> viewModelRepo.filterGitHubRepos(0)
            "Forks" -> viewModelRepo.filterGitHubRepos(1)
            "Updates" -> viewModelRepo.filterGitHubRepos(2)
        }
    }
}
