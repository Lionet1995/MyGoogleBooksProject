package com.example.mygooglebooksproject.domain.interfaces

import com.example.mygooglebooksproject.domain.models.Book

interface IBooksRepository {
    suspend fun getBooks(entry: String): List<Book>
}