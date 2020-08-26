package com.anikulki.daggergithub.githubapi

import com.anikulki.daggergithub.githubapi.model.RepoApiModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopReposSearchResult(val items: List<RepoApiModel>)