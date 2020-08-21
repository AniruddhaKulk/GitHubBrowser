package com.anikulki.daggergithub.application

import android.content.Context
import com.anikulki.daggergithub.appdeps.ApplicationDeps
import com.anikulki.daggergithub.githubapi.GitHubApiModule
import com.anikulki.daggergithub.repository.AppRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GitHubApiModule::class])
interface ApplicationComponent: ApplicationDeps {

    @Component.Factory
    interface Factory {

        //dagger will generate the implementation of this interface
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}