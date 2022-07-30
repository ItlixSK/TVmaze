package com.example.tvmaze.repository

import com.example.tvmaze.api.ApiService
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTvShows() = apiService.getTvShow()
}