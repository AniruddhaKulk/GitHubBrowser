package com.anikulki.daggergithub.testing.app.githubapi

import com.anikulki.daggergithub.githubapi.GitHubApi
import com.anikulki.daggergithub.githubapi.TopReposSearchResult
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGitHubApi @Inject constructor(): GitHubApi {

    var repos = listOf<RepoApiModel>()

    override suspend fun getTopRepositories(): TopReposSearchResult {
        return TopReposSearchResult(repos)
    }


}