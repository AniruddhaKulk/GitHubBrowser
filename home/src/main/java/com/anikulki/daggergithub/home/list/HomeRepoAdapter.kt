package com.anikulki.daggergithub.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anikulki.daggergithub.home.databinding.ItemRepositoriesBinding

class HomeRepoAdapter(
    private val onRepoSelected: (repoOwner: String, repoName: String) -> Unit
): RecyclerView.Adapter<HomeRepoAdapter.RepoHolder>(){

    private val repoList: MutableList<RepositoryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val binding = ItemRepositoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RepoHolder(binding, onRepoSelected)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(repoList[position])
    }

    class RepoHolder(private val binding: ItemRepositoriesBinding,
                     onRepoSelected: (repoOwner: String, repoName: String) -> Unit)
        :RecyclerView.ViewHolder(binding.root)  {

        private var repoItem: RepositoryItem? = null

        init {
            itemView.setOnClickListener {
                repoItem?.let { repo -> onRepoSelected(repo.ownerName, repo.name) }
            }
        }

        fun bind(item: RepositoryItem){
            repoItem = item
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