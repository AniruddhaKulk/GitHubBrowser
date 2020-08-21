package com.anikulki.daggergithub.home.list

data class RepositoryItem (
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int
)