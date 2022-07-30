package com.example.tvmaze.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmaze.model.TvModelItem
import com.example.tvmaze.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val repository: TvShowRepository) :
    ViewModel() {

    private val mResponse = MutableLiveData<List<TvModelItem>>()
    val responseTvShow: LiveData<List<TvModelItem>>
        get() = mResponse

    init {
        getAllTvShows()
    }

    private fun getAllTvShows() = viewModelScope.launch {
        repository.getTvShows().let { response ->
            if (response.isSuccessful) {
                mResponse.postValue(response.body())
            } else {
                Log.d("tag", "getAllTvShows Error: ${response.code()}")
            }
        }

    }
}