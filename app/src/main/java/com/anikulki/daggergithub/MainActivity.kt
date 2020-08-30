package com.anikulki.daggergithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anikulki.daggergithub.home.HomeFragment

class MainActivity : AppCompatActivity() {

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
}