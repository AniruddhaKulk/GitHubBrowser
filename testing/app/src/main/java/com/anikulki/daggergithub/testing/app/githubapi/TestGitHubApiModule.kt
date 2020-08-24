package com.anikulki.daggergithub.testing.app.githubapi

import com.anikulki.daggergithub.githubapi.GitHubApi
import dagger.Binds
import dagger.Module


@Module
interface TestGitHubApiModule {

    @Binds
    fun bindGitHubApi(fakeGitHubApi: FakeGitHubApi): GitHubApi
}