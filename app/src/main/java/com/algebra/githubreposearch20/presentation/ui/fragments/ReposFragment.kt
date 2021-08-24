package com.algebra.githubreposearch20.presentation.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.FragmentReposBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.presentation.ui.adapter.RepoAdapter
import com.algebra.githubreposearch20.presentation.ui.dialog.DialogFilterListener
import com.algebra.githubreposearch20.presentation.ui.helper.ProgressBarHelper
import com.algebra.githubreposearch20.presentation.ui.helper.RefreshHelper
import com.algebra.githubreposearch20.presentation.ui.helper.SearchResultHelper
import com.algebra.githubreposearch20.presentation.ui.viewmodel.GitHubRepoViewModel
import com.algebra.githubreposearch20.util.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment(R.layout.fragment_repos), DialogFilterListener {

    private lateinit var binding: FragmentReposBinding
    private lateinit var searchView: SearchView
    private val progressBarHelper = ProgressBarHelper()
    private val searchResultHelper = SearchResultHelper()
    private lateinit var refreshHelper: RefreshHelper

    private val viewModelRepo: GitHubRepoViewModel by viewModel()
    private lateinit var adapter: RepoAdapter

    private val sharedPref: SharedPreferences by inject()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repos, null, false)

        refreshHelper = RefreshHelper(viewModelRepo)
        binding.apply {
            helper = progressBarHelper
            searchResult = searchResultHelper
            refresh = refreshHelper
        }

        adapter = RepoAdapter(activity as MainActivity, binding.root)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            exitFromApp(requireActivity() as MainActivity)
        }

        getLastSearchRepos()
        bind()
        setUpRecyclerView()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_repos_fragment, menu)
        val searchItem = menu.findItem(R.id.searchIcon)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.searchQuery)

        searchAction(searchView, requireContext(), viewModelRepo, refreshHelper)
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
            progressBarHelper.setLoading(false)
            searchResultHelper.setSearchResult(getString(R.string.search_message))
        } else {
            progressBarHelper.setLoading(false)
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
                        progressBarHelper.setLoading(false)
                        searchResultHelper.setSearchResult("")
                        adapter.setList(result.value)
                        val editor = sharedPref.edit()
                        val jsonOfGitHubRepos = gson.toJson(result.value)
                        editor.putString(Constants.SEARCH_REPOS, jsonOfGitHubRepos).apply()
                    }
                    is Resource.Failure -> {
                        progressBarHelper.setLoading(false)
                        searchResultHelper.setSearchResult("")
                        displayMessage(result.message, requireContext())
                    }
                    is Resource.Loading -> progressBarHelper.setLoading(true)
                    is Resource.Empty -> {
                        progressBarHelper.setLoading(false)
                        adapter.setList(emptyList())
                        searchResultHelper.setSearchResult(getString(R.string.repo_not_found))
                    }
                }
            }
        )
    }

    private fun setUpRecyclerView() {
        binding.apply {
            rvRepos.layoutManager = LinearLayoutManager(requireContext())
            rvRepos.adapter = adapter
        }
    }

    override fun pressFilter(filter: String) {
        when (filter) {
            "Stars" -> viewModelRepo.filterGitHubRepos(0)
            "Forks" -> viewModelRepo.filterGitHubRepos(1)
            "Updates" -> viewModelRepo.filterGitHubRepos(2)
        }
    }
}
