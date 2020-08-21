package com.anikulki.daggergithub.repository

import com.anikulki.daggergithub.githubapi.GitHubApi
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val gitHubApi: GitHubApi
){

    fun getTopRepositories(): List<RepoApiModel> {
        return gitHubApi.getTopRepositories()
    }
}