package com.anikulki.daggergithub.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/*
    MapKey - used to binding dependencies in to a multibind dagger.
    MapKey - Tells dagger that this annotation is used to specify keys to the maps
    out ViewModel - Upper bound and only using this annotation with ViewModel subclasses
 */

@MapKey
annotation class ViewModelKey (val classKey: KClass<out ViewModel>)