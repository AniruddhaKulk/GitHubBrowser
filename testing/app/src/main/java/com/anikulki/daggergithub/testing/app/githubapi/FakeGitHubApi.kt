package com.anikulki.daggergithub.testing.app.githubapi

import com.anikulki.daggergithub.githubapi.GitHubApi
import com.anikulki.daggergithub.githubapi.TopReposSearchResult
import com.anikulki.daggergithub.githubapi.model.ContributorApiModel
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGitHubApi @Inject constructor(): GitHubApi {

    var topRepos = listOf<RepoApiModel>()
    var singleRepoResult: RepoApiModel? = null
    var contributorsResult = listOf<ContributorApiModel>()

    override suspend fun getTopRepositories(): TopReposSearchResult {
        return TopReposSearchResult(topRepos)
    }

    override suspend fun getRepo(repoOwner: String, repoName: String): RepoApiModel {
        return singleRepoResult ?: throw NullPointerException("singleRepoResult was not set")
    }

    override suspend fun getContributors(
        repoOwner: String,
        repoName: String
    ): List<ContributorApiModel> {
        return contributorsResult
    }
}