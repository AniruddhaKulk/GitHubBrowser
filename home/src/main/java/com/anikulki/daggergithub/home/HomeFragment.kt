package com.anikulki.daggergithub.home

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
import com.anikulki.daggergithub.di.viewmodel.AppViewModelFactory
import com.anikulki.daggergithub.home.databinding.HomeFragmentBinding
import com.anikulki.daggergithub.home.list.HomeRepoAdapter
import javax.inject.Inject

class HomeFragment: Fragment() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, appViewModelFactory)[HomeViewModel::class.java]
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
        val binding = HomeFragmentBinding.inflate(inflater, container, false)

        binding.rvRepoList.apply {
            adapter = HomeRepoAdapter()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        homeViewModel.viewStateUpdate.observe(viewLifecycleOwner, Observer{state ->

            when(state){
                is HomeViewStateLoading -> handleLoadingState(binding)
                is HomeViewStateLoaded -> handleLoadedState(state, binding)
                is HomeViewStateError -> handleErrorState(state, binding)
            }

        })


        return binding.root
    }

    private fun handleLoadedState(state: HomeViewStateLoaded, binding: HomeFragmentBinding) {
        binding.rvRepoList.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        (binding.rvRepoList.adapter as HomeRepoAdapter).setRepoList(state.list)
    }

    private fun handleErrorState(state: HomeViewStateError, binding: HomeFragmentBinding) {
        binding.rvRepoList.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        binding.tvError.text = state.message
    }

    private fun handleLoadingState(binding: HomeFragmentBinding) {
        binding.rvRepoList.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }
}