package com.anikulki.daggergithub

import com.anikulki.daggergithub.di.component.getComponent
import com.anikulki.daggergithub.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(): MainActivityComponent
    }
}

fun MainActivity.injectAndGetComponent(): MainActivityComponent {
    val component = getComponent { DaggerMainActivityComponent.create() }
    component.inject(this)
    return component
}