package com.anikulki.daggergithub.testing.app.navigation

import com.anikulki.daggergithub.navigation.Screen
import com.anikulki.daggergithub.navigation.ScreenNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeScreenNavigator @Inject constructor() : ScreenNavigator {

    override fun goToScreen(screen: Screen) {

    }
}