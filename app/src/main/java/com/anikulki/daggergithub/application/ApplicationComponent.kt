package com.anikulki.daggergithub.application

import android.content.Context
import com.anikulki.daggergithub.githubapi.GitHubApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GitHubApiModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        //dagger will generate the implementation of this interface
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}