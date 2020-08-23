package com.anikulki.daggergithub.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anikulki.daggergithub.githubapi.GitHubApi
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import com.anikulki.daggergithub.home.list.RepositoryItem
import com.anikulki.daggergithub.repository.AppRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
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

class HomeViewModelTest {

    /*
        LiveData calls back on main thread. While unit testing, we need something that
        skips the mainLooper(), since that present. Hence this rule is added to skip it
     */
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewStateValues: MutableList<HomeViewState>

    @Before
    fun setUp() {
        val appRepo = AppRepository(FakeGitHubApi())
        viewStateValues = mutableListOf()

        viewModel = HomeViewModel(appRepo)
        viewModel.viewStateUpdate.observeForever{viewStateValues.add(it)}
    }

    @Test
    fun `loaded state contains repo models`(){
        assertThat(viewStateValues.size).isEqualTo(1)
        val expectedState = HomeViewStateLoaded(
            listOf(
                RepositoryItem(
                    name = fakeRepoApiModel.name,
                    description = fakeRepoApiModel.description,
                    starCount = fakeRepoApiModel.stargazersCount,
                    forkCount = fakeRepoApiModel.forksCount
                )
            )
        )

        assertThat(viewStateValues[0]).isEqualTo(expectedState)
    }

}

private class FakeGitHubApi: GitHubApi {

    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(fakeRepoApiModel)
    }

}