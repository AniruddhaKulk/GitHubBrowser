package com.anikulki.daggergithub.repository

import com.anikulki.daggergithub.githubapi.GitHubApi
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

private val fakeRepoApiModel = RepoApiModel(
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

class AppRepositoryTest {

    private lateinit var appRepository: AppRepository

    @Before
    fun setup(){
        appRepository = AppRepository(FakeGitHubApi())
    }

    @Test
    fun successfulQuery(){
        val topRepos = appRepository.getTopRepositories()

        assertThat(topRepos.size).isEqualTo(1)
        assertThat(topRepos[0]).isEqualTo(fakeRepoApiModel)
    }
}


private class FakeGitHubApi: GitHubApi {

    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(fakeRepoApiModel)
    }

}