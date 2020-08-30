package com.anikulki.daggergithub

import com.anikulki.daggergithub.di.scope.ActivityScope
import com.anikulki.daggergithub.navigation.Screen
import com.anikulki.daggergithub.navigation.ScreenNavigator
import javax.inject.Inject

@ActivityScope
class ActivityDrivenScreenNavigator @Inject constructor() : ScreenNavigator {

    var handleGoToScreen: ((Screen) -> Unit)? = null

    override fun goToScreen(screen: Screen) {
        handleGoToScreen?.invoke(screen)
    }
}