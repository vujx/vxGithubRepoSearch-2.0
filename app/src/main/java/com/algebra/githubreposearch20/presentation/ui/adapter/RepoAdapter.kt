package com.algebra.githubreposearch20.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.algebra.githubreposearch20.databinding.ItemRepoBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo

class RepoAdapter(private val listener: RepoAdapterListener) : RecyclerView.Adapter<RepoViewHolder>() {

    private val listOfRepo = mutableListOf<GitHubRepo>()

    fun setList(list: List<GitHubRepo>) {
        listOfRepo.clear()
        listOfRepo.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding, listOfRepo, listener)
    }

    override fun getItemCount(): Int = listOfRepo.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(listOfRepo[position])
    }
}
