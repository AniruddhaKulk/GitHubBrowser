package com.anikulki.daggergithub.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anikulki.daggergithub.home.list.RepositoryItem
import com.anikulki.daggergithub.repository.AppRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel(){

    private val _viewState = MutableLiveData<HomeViewState>(HomeViewStateLoading)
    val viewStateUpdate: LiveData<HomeViewState> = _viewState

    init {
        val topRepos = appRepository.getTopRepositories()
        _viewState.value = HomeViewStateLoaded(
            list = topRepos.map {
                RepositoryItem(
                    it.name,
                    it.description,
                    it.stargazersCount,
                    it.forksCount
                )
            }
        )
    }

}