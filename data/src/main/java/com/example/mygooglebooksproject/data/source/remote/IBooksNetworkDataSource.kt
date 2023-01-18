package com.example.mygooglebooksproject.data.source.remote

import com.example.mygooglebooksproject.data.models.SearchResult

interface IBooksNetworkDataSource {
    suspend fun getBooks(entry: String): SearchResult
}