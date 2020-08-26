package com.anikulki.daggergithub.githubapi

import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubApi {

    @GET("search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getTopRepositories(): TopReposSearchResult
}

@Singleton
class MockGitHubApi @Inject constructor() : GitHubApi {

    override suspend fun getTopRepositories(): TopReposSearchResult {
        return TopReposSearchResult(
            listOf(
                RepoApiModel(
                    id = 1L,
                    name = "Mock Repo",
                    description = "Mock Repo Description",
                    owner = UserApiModel(id = 1L, login = "dagger"),
                    stargazersCount = 1,
                    forksCount = 1,
                    contributorsUrl = "",
                    createdDate = "1/1/2020",
                    updatedDate = "1/1/2020"
                ),
                RepoApiModel(
                    id = 1L,
                    name = "Mock Repo",
                    description = "Mock Repo Description",
                    owner = UserApiModel(id = 1L, login = "dagger"),
                    stargazersCount = 1,
                    forksCount = 1,
                    contributorsUrl = "",
                    createdDate = "1/1/2020",
                    updatedDate = "1/1/2020"
                ),
                RepoApiModel(
                    id = 1L,
                    name = "Mock Repo",
                    description = "Mock Repo Description",
                    owner = UserApiModel(id = 1L, login = "dagger"),
                    stargazersCount = 1,
                    forksCount = 1,
                    contributorsUrl = "",
                    createdDate = "1/1/2020",
                    updatedDate = "1/1/2020"
                ),
                RepoApiModel(
                    id = 1L,
                    name = "Mock Repo",
                    description = "Mock Repo Description",
                    owner = UserApiModel(id = 1L, login = "dagger"),
                    stargazersCount = 1,
                    forksCount = 1,
                    contributorsUrl = "",
                    createdDate = "1/1/2020",
                    updatedDate = "1/1/2020"
                )
            )
        )
    }

}