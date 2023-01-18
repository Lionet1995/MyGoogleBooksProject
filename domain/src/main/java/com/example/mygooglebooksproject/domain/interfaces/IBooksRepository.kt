package com.example.mygooglebooksproject.domain.interfaces

import com.example.mygooglebooksproject.domain.models.Book

interface IBooksRepository {
    suspend fun getBooksFromNetwork(entry: String) : List<Book>
    suspend fun getBooksFromDB() : List<Book>
}