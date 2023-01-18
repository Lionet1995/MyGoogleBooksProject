package com.example.mygooglebooksproject.data.source.local

import com.example.mygooglebooksproject.data.models.RoomBookModel
import com.example.mygooglebooksproject.domain.models.Book

interface IBooksLocalDataSource {
    suspend fun getBooks() : List<Book>
    suspend fun refreshSavedBooks(books: List<RoomBookModel>)
}