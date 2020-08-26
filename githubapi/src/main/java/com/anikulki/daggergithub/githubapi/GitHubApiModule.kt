package com.anikulki.daggergithub.githubapi

import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
object GitHubApiModule {

    /*
        This function is telling dagger that whenever someone requires GitHubApi interface,
        give them this implementation.

        We are binding the implementation that is declared as function argument to the
        type of the return type

        Once the repository is added, it will inject this dependency

        Requirement while using the "@Binds" is the argument needs to be injectable
     */

    /*@Binds
    fun bindGitHubApi(mockGitHubApi: MockGitHubApi): GitHubApi*/

    @Provides
    @JvmStatic
    @Singleton
    fun provideOkHttp(): Call.Factory {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        callFactory: Call.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .callFactory(callFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create()
    }
}