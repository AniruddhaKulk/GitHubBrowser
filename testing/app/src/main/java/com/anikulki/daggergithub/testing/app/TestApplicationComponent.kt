package com.anikulki.daggergithub.testing.app

import android.content.Context
import com.anikulki.daggergithub.appcomponent.ApplicationComponent
import com.anikulki.daggergithub.navigation.NavigationDeps
import com.anikulki.daggergithub.testing.app.githubapi.FakeGitHubApi
import com.anikulki.daggergithub.testing.app.githubapi.TestGitHubApiModule
import com.anikulki.daggergithub.testing.app.navigation.TestNavigationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestGitHubApiModule::class, TestNavigationModule::class])
interface TestApplicationComponent: ApplicationComponent, NavigationDeps{

    fun gitHubApi(): FakeGitHubApi

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): TestApplicationComponent
    }

}
