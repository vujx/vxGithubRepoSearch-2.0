package com.algebra.githubreposearch20.presentation.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.FragmentReposBinding
import com.algebra.githubreposearch20.util.viewBinding

class ReposFragment : Fragment(R.layout.fragment_repos) {

    private val binding by viewBinding(FragmentReposBinding::bind)
    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repos_fragment, menu)
        val searchItem = menu.findItem(R.id.searchIcon)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.searchQuery)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filterIcon -> { true }
            else -> true
        }
    }
}
