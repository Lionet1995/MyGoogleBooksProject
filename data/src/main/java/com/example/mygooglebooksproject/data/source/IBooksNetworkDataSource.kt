package com.example.mygooglebooksproject.data.source

import com.example.mygooglebooksproject.data.models.SearchResult

interface IBooksNetworkDataSource {
    suspend fun getBooks(entry: String): SearchResult
}