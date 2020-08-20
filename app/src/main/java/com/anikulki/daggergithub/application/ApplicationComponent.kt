package com.anikulki.daggergithub.application

import android.content.Context
import com.anikulki.daggergithub.githubapi.GitHubApiModule
import com.anikulki.daggergithub.repository.AppRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GitHubApiModule::class])
interface ApplicationComponent {

    /*Any other components that depends on the ApplicationComponent
        will now be able to inject AppRepository
     */
    fun appRepository(): AppRepository

    @Component.Factory
    interface Factory {

        //dagger will generate the implementation of this interface
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}