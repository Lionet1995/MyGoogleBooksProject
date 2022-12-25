package com.example.mygooglebooksproject.data.source

import com.example.mygooglebooksproject.data.api.BooksService
import com.example.mygooglebooksproject.data.models.SearchResult
import javax.inject.Inject

class BooksNetworkDataSource @Inject constructor(private val booksService: BooksService) : IBooksNetworkDataSource {
    override suspend fun getBooks(entry: String): SearchResult {
        var books = SearchResult(listOf())
        try {
            books = booksService.getBooks(entry)
        } catch (e: Exception) {

        }
        return books
    }

}