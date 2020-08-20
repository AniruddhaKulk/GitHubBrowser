package com.anikulki.daggergithub.githubapi

import dagger.Binds
import dagger.Module

@Module
interface GitHubApiModule {

    /*
        This function is telling dagger that whenever someone requires GitHubApi interface,
        give them this implementation.

        We are binding the implementation that is declared as function argument to the
        type of the return type

        Once the repository is added, it will inject this dependency

        Requirement while using the "@Binds" is the argument needs to be injectable
     */

    @Binds
    fun bindGitHubApi(mockGitHubApi: MockGitHubApi): GitHubApi
}