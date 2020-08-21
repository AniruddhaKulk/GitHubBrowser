package com.anikulki.daggergithub.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anikulki.daggergithub.home.databinding.ItemRepositoriesBinding

class HomeRepoAdapter: RecyclerView.Adapter<HomeRepoAdapter.RepoHolder>(){

    private val repoList: MutableList<RepositoryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val binding = ItemRepositoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RepoHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(repoList[position])
    }

    class RepoHolder(private val binding: ItemRepositoriesBinding)
        :RecyclerView.ViewHolder(binding.root)  {

        fun bind(item: RepositoryItem){
            binding.tvRepoName.text = item.name
            binding.tvRepoDescription.text = item.description
            binding.tvStarCount.text = "${item.starCount}"
            binding.tvForkCount.text = "${item.forkCount}"
        }
    }

    fun setRepoList(list: List<RepositoryItem>){
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }
}