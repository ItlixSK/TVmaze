package com.example.tvmaze

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvmaze.adapter.TvShowAdapter
import com.example.tvmaze.databinding.ActivityMainBinding
import com.example.tvmaze.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()

    }

    private fun setUpRv() {
        tvShowAdapter = TvShowAdapter()

        binding.recyclerView.apply {
            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }

        viewModel.responseTvShow.observe(this) { listTvShow ->
            tvShowAdapter.tvShows = listTvShow
        }
    }
}