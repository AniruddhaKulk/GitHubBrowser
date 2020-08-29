package com.anikulki.daggergithub.githubapi

import com.anikulki.daggergithub.githubapi.model.ContributorApiModel
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubApi {

    @GET("search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getTopRepositories(): TopReposSearchResult

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") repoOwner: String,
        @Path("name") repoName: String
    ): RepoApiModel

    @GET("repos/{owner}/{name}/contributors")
    suspend fun getContributors(
        @Path("owner") repoOwner: String,
        @Path("name") repoName: String
    ): List<ContributorApiModel>
}