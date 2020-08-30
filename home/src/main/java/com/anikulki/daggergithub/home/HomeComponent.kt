package com.anikulki.daggergithub.home

import com.anikulki.daggergithub.appdeps.ApplicationDeps
import com.anikulki.daggergithub.appdeps.applicationDeps
import com.anikulki.daggergithub.di.component.getComponent
import com.anikulki.daggergithub.di.scope.HomeScope
import com.anikulki.daggergithub.navigation.NavigationDeps
import com.anikulki.daggergithub.navigation.navigationDeps
import dagger.Component

@HomeScope
@Component(dependencies = [ApplicationDeps::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Factory
    interface Factory{

        fun create(
            applicationDeps: ApplicationDeps,
            navigationDeps: NavigationDeps
        ): HomeComponent
    }
}

fun HomeFragment.inject(){
    getComponent {
        DaggerHomeComponent.factory().create(
            requireContext().applicationDeps(),
            requireActivity().navigationDeps()
        )
    }.inject(this)
}