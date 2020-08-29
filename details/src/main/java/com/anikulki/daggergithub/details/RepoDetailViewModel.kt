package com.anikulki.daggergithub.details

import androidx.lifecycle.ViewModel
import com.anikulki.daggergithub.di.scope.HomeScope
import com.anikulki.daggergithub.repository.AppRepository
import javax.inject.Inject
import javax.inject.Named

@HomeScope
class RepoDetailsViewModel @Inject constructor(
    @Named("repo_owner") private val repoOwner: String,
    @Named("repo_name") private val repoName: String,
    private val appRepository: AppRepository
) : ViewModel()