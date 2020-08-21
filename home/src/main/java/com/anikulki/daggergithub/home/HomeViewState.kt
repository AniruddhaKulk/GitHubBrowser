package com.anikulki.daggergithub.home

import com.anikulki.daggergithub.home.list.RepositoryItem

/*
    Advantage of sealed class is -
    way of ensuring exhaustive handling in when statements, as well as smart casting
 */
sealed class HomeViewState
object HomeViewStateLoading: HomeViewState()
data class HomeViewStateLoaded(val list: List<RepositoryItem>): HomeViewState()
data class HomeViewStateError(val message: String): HomeViewState()