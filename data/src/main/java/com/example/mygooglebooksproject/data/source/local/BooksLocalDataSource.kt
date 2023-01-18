package com.example.mygooglebooksproject.data.source.local

import com.example.mygooglebooksproject.data.models.RoomBookModel
import com.example.mygooglebooksproject.data.models.convertToDomainModel
import com.example.mygooglebooksproject.data.storages.BooksDatabase
import com.example.mygooglebooksproject.domain.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksLocalDataSource @Inject constructor(private val database: BooksDatabase): IBooksLocalDataSource {

    override suspend fun getBooks(): List<Book> = database.booksDao().loadBooks().convertToDomainModel()

    override suspend fun refreshSavedBooks(books: List<RoomBookModel>) = database.booksDao().refreshBooks(books)
}