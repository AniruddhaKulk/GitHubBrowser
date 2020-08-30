package com.anikulki.daggergithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anikulki.daggergithub.details.RepoDetailsFragment
import com.anikulki.daggergithub.home.HomeFragment
import com.anikulki.daggergithub.navigation.DetailsScreen
import com.anikulki.daggergithub.navigation.NAVIGATION_DEPS_SERVICE
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var screenNavigator: ActivityDrivenScreenNavigator

    private lateinit var component: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = injectAndGetComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, HomeFragment())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        screenNavigator.handleGoToScreen = { screen ->
            when (screen) {
                is DetailsScreen -> supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        RepoDetailsFragment.newInstance(screen.repoOwner, screen.repoName)
                    )
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun onStop() {
        screenNavigator.handleGoToScreen = null
        super.onStop()
    }

    override fun getSystemService(name: String): Any? {
        if (name == NAVIGATION_DEPS_SERVICE) {
            return component
        }
        return super.getSystemService(name)
    }
}