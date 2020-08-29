package com.anikulki.daggergithub.details

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anikulki.daggergithub.githubapi.model.ContributorApiModel
import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.anikulki.daggergithub.githubapi.model.UserApiModel
import com.anikulki.daggergithub.testing.app.TestApplication

@RunWith(AndroidJUnit4::class)
class RepoDetailsFragmentTest {

    private val fakeRepoApiModel = RepoApiModel(
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

    private val fakeContributor =
        ContributorApiModel(id = 1L, login = "contributor", avatarUrl = "avatar.png")

    @Before
    fun setUp() {
        val gitHubApi = TestApplication.component.gitHubApi()
        gitHubApi.singleRepoResult = fakeRepoApiModel
        gitHubApi.contributorsResult = listOf(fakeContributor)
    }

    @Test
    fun loadedStateDisplaysExpectedData() {
        launchFragmentInContainer<RepoDetailsFragment>(
            fragmentArgs = Bundle().apply {
                putString("repo_owner", "owner")
                putString("repo_name", "name")
            }
        )

        onView(withId(R.id.detailProgressBar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.progressBar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.tvRepoName)).check(matches(withText(fakeRepoApiModel.name)))
        onView(withId(R.id.tvRepoDescription))
            .check(matches(withText(fakeRepoApiModel.description)))

        onView(withId(R.id.contributorName)).check(matches(withText(fakeContributor.login)))
    }
}