package com.anikulki.daggergithub.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import com.anikulki.daggergithub.home.list.RepositoryItem
import com.anikulki.daggergithub.navigation.DetailsScreen
import com.anikulki.daggergithub.repository.AppRepository
import com.anikulki.daggergithub.testing.app.githubapi.FakeGitHubApi
import com.anikulki.daggergithub.testing.app.navigation.FakeScreenNavigator
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.test.setMain
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
        skips the mainLooper(), since that is not present. Hence this rule is added to skip it
     */
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewStateValues: MutableList<HomeViewState>
    private lateinit var screenNavigator: FakeScreenNavigator

    @Before
    fun setUp() {
        //Unit tests do not have loopers, hence add the below line
        Dispatchers.setMain(Dispatchers.Unconfined)
        val appRepo = AppRepository(FakeGitHubApi().apply { topRepos = listOf(fakeRepoApiModel) })
        viewStateValues = mutableListOf()
        screenNavigator = FakeScreenNavigator()

        viewModel = HomeViewModel(appRepo, screenNavigator)
        viewModel.viewStateUpdate.observeForever{viewStateValues.add(it)}
    }

    @Test
    fun `loaded state contains repo models`(){
        assertThat(viewStateValues.size).isEqualTo(1)
        val expectedState = HomeViewStateLoaded(
            listOf(
                RepositoryItem(
                    ownerName = fakeRepoApiModel.owner.login,
                    name = fakeRepoApiModel.name,
                    description = fakeRepoApiModel.description ?: "",
                    starCount = fakeRepoApiModel.stargazersCount,
                    forkCount = fakeRepoApiModel.forksCount
                )
            )
        )

        assertThat(viewStateValues[0]).isEqualTo(expectedState)
    }


    @Test
    fun `repoSelected calls goToScreen`() {
        viewModel.onRepoSelected(fakeRepoApiModel.owner.login, fakeRepoApiModel.name)

        val expectedScreen = DetailsScreen(fakeRepoApiModel.owner.login, fakeRepoApiModel.name)

        assertThat(screenNavigator.openedScreens.size).isEqualTo(1)
        assertThat(screenNavigator.openedScreens[0]).isEqualTo(expectedScreen)
    }

}
