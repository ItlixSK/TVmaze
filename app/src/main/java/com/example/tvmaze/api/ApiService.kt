package com.example.tvmaze.api

import android.provider.SyncStateContract
import com.example.tvmaze.END_POINT
import com.example.tvmaze.model.TvModelResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getTvShow():Response<TvModelResponse>
}