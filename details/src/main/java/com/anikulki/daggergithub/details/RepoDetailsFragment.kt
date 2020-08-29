package com.anikulki.daggergithub.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anikulki.daggergithub.details.databinding.FragmentDetailBinding
import com.anikulki.daggergithub.details.list.ContributorsAdapter
import com.anikulki.daggergithub.di.viewmodel.AppViewModelFactory
import javax.inject.Inject

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance(repoOwner: String, repoName: String): RepoDetailsFragment {
            val args = Bundle().apply {
                putString("repo_name", repoName)
                putString("repo_owner", repoOwner)
            }
            return RepoDetailsFragment().apply { arguments = args }
        }
    }

    @Inject lateinit var viewModelFactory: AppViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RepoDetailsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.rvList.apply {
            adapter = ContributorsAdapter()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        viewModel.repoInfoUpdates.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is RepoInfoViewStateLoading -> handleInfoLoadingState(binding)
                is RepoInfoViewStateLoaded -> handleInfoLoadedState(binding, state)
            }
        })
        viewModel.contributorsUpdates.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is RepoContributorsViewStateLoading -> handleContributorsLoadingState(binding)
                is RepoContributorsViewStateLoaded -> handleContributorsLoadedState(binding, state)
            }
        })
        return binding.root
    }

    private fun handleContributorsLoadedState(
        binding: FragmentDetailBinding,
        state: RepoContributorsViewStateLoaded
    ) {
        binding.progressBar.visibility = View.GONE
        binding.rvList.visibility = View.VISIBLE

        (binding.rvList.adapter as ContributorsAdapter).setContributors(state.contributors)
    }

    private fun handleContributorsLoadingState(binding: FragmentDetailBinding) {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvList.visibility = View.GONE
    }

    private fun handleInfoLoadedState(
        binding: FragmentDetailBinding,
        state: RepoInfoViewStateLoaded
    ) {
        binding.detailProgressBar.visibility = View.GONE
        binding.toggleInfoTextVisibility(View.VISIBLE)

        binding.tvRepoName.text = state.repoName
        binding.tvRepoDescription.text = state.repoDescription
        binding.tvCreationDate.text = state.createdDate
        binding.tvUpdatedDate.text = state.updatedDate
    }

    private fun handleInfoLoadingState(binding: FragmentDetailBinding) {
        binding.detailProgressBar.visibility = View.VISIBLE
        binding.toggleInfoTextVisibility(View.GONE)
    }

    private fun FragmentDetailBinding.toggleInfoTextVisibility(visibility: Int) {
        tvRepoName.visibility = visibility
        tvRepoDescription.visibility = visibility
        tvCreationDateLabel.visibility = visibility
        tvCreationDate.visibility = visibility
        tvUpdatedDateLabel.visibility = visibility
        tvUpdatedDate.visibility = visibility
    }
}