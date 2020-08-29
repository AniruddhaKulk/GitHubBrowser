package com.anikulki.daggergithub.details

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anikulki.daggergithub.details.list.ContributorItem
import com.anikulki.daggergithub.githubapi.model.ContributorApiModel
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import com.anikulki.daggergithub.repository.AppRepository
import com.anikulki.daggergithub.testing.app.githubapi.FakeGitHubApi
import com.google.common.truth.Truth.assertThat

class RepoDetailsViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RepoDetailsViewModel
    private lateinit var repoInfoViewStateValues: MutableList<RepoInfoViewState>
    private lateinit var repoContributorsViewStateValues: MutableList<RepoContributorsViewState>

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

    private val fakeContributorsList = listOf(
        ContributorApiModel(id = 1L, login = "contributor", avatarUrl = "avatar.png")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        repoInfoViewStateValues = mutableListOf()
        repoContributorsViewStateValues = mutableListOf()

        val appRepository = AppRepository(FakeGitHubApi().apply {
            singleRepoResult = fakeRepoApiModel
            contributorsResult = fakeContributorsList
        })

        viewModel = RepoDetailsViewModel("repo_owner", "repo_name", appRepository)
        viewModel.repoInfoUpdates.observeForever { repoInfoViewStateValues.add(it) }
        viewModel.contributorsUpdates.observeForever { repoContributorsViewStateValues.add(it) }
    }

    @Test
    fun `repo info loaded`() {
        assertThat(repoInfoViewStateValues.size).isEqualTo(1)
        val expectedState = RepoInfoViewStateLoaded(
            repoName = fakeRepoApiModel.name,
            repoDescription = fakeRepoApiModel.description ?: "",
            createdDate = fakeRepoApiModel.createdDate,
            updatedDate = fakeRepoApiModel.updatedDate
        )

        assertThat(repoInfoViewStateValues[0]).isEqualTo(expectedState)
    }

    @Test
    fun `repo contributors loaded`() {
        assertThat(repoContributorsViewStateValues.size).isEqualTo(1)
        val expectedState = RepoContributorsViewStateLoaded(
            contributors = fakeContributorsList.map { apiModel ->
                ContributorItem(
                    id = apiModel.id,
                    name = apiModel.login,
                    avatarUrl = apiModel.avatarUrl
                )
            }
        )

        assertThat(repoContributorsViewStateValues[0]).isEqualTo(expectedState)
    }
}