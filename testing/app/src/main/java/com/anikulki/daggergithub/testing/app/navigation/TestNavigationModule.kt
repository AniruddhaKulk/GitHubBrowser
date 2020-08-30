package com.anikulki.daggergithub.testing.app.navigation

import com.anikulki.daggergithub.navigation.ScreenNavigator
import dagger.Binds
import dagger.Module

@Module
interface TestNavigationModule {

    @Binds
    fun bindScreenNavigator(fakeScreenNavigator: FakeScreenNavigator): ScreenNavigator
}