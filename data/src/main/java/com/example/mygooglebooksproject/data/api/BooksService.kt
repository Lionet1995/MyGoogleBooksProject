package com.example.mygooglebooksproject.data.api

import com.example.mygooglebooksproject.data.models.SearchResult
import com.example.mygooglebooksproject.data.utils.Configs
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {
    @GET("volumes?")

     suspend fun getBooks(
        @Query(ENTRY) entry: String,
        @Query(MAX_RESULTS_KEY) maxResults: String = Configs.MAX_RESULTS_VALUE,
        @Query(API_KEY) apiKey: String = Configs.API_KEY
    ) : SearchResult

    companion object {
        private const val API_KEY = "key"
        private const val ENTRY = "q"
        private const val MAX_RESULTS_KEY = "maxResults"
    }
}