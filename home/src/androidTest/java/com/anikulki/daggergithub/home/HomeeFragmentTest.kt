package com.anikulki.daggergithub.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import com.anikulki.daggergithub.testing.app.TestApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeeFragmentTest {

    @Before
    fun setUp() {
        val gitHubApi = TestApplication.component.gitHubApi()
        gitHubApi.topRepos = listOf(
            RepoApiModel(
                id = 1L,
                name = "Home Fragment",
                description = "Mock Repo Description",
                owner = UserApiModel(id = 1L, login = "dagger"),
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/1/2020",
                updatedDate = "1/1/2020"
            )
        )
    }

    @Test
    fun reposDisplayed() {
        //this will launch the fragment in onResumed() state
        launchFragmentInContainer<HomeFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.tvRepoName))
            .check(ViewAssertions.matches(ViewMatchers.withText("Home Fragment")))
    }
}