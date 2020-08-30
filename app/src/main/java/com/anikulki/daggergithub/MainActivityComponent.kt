package com.anikulki.daggergithub

import com.anikulki.daggergithub.di.component.getComponent
import com.anikulki.daggergithub.di.scope.ActivityScope
import com.anikulki.daggergithub.navigation.ScreenNavigator
import dagger.Binds
import dagger.Component
import dagger.Module

@ActivityScope
@Component(modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(): MainActivityComponent
    }
}

@Module
interface MainActivityModule {

    @Binds
    fun bindScreenNavigator(activityDrivenScreenNavigator: ActivityDrivenScreenNavigator)
            : ScreenNavigator
}

fun MainActivity.injectAndGetComponent(): MainActivityComponent {
    val component = getComponent { DaggerMainActivityComponent.create() }
    component.inject(this)
    return component
}