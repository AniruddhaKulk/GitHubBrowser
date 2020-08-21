package com.anikulki.daggergithub.application

import android.app.Application
import com.anikulki.daggergithub.appdeps.ApplicationDeps
import com.anikulki.daggergithub.appdeps.HasApplicationDeps

class GithubBrowserApp: Application(), HasApplicationDeps {

    private val appComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getApplicationDeps(): ApplicationDeps {
        return appComponent
    }
}