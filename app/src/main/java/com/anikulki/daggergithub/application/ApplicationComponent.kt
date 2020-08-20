package com.anikulki.daggergithub.application

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        //dagger will generate the implementation of this interface
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}