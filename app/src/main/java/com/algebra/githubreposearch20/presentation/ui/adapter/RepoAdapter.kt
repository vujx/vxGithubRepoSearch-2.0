package com.algebra.githubreposearch20.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.ItemRepoBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.presentation.ui.fragments.Helper

class RepoAdapter(
    val activity: AppCompatActivity,
    var view: View?
) : RecyclerView.Adapter<RepoViewHolder>() {

    private val listOfRepo = mutableListOf<GitHubRepo>()

    fun setList(list: List<GitHubRepo>) {
        listOfRepo.clear()
        listOfRepo.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding: ItemRepoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false)
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfRepo.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val helper = Helper(activity, listOfRepo[position], view)
        holder.itemRepo.helper = helper
        holder.itemRepo.repo = listOfRepo[position]
    }
}
