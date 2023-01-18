package com.example.mygooglebooksproject.data.repositories

import android.util.Log
import com.example.mygooglebooksproject.data.models.convertToDatabaseModel
import com.example.mygooglebooksproject.data.models.convertToDomainModel
import com.example.mygooglebooksproject.data.source.local.IBooksLocalDataSource
import com.example.mygooglebooksproject.data.source.remote.IBooksNetworkDataSource
import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import com.example.mygooglebooksproject.domain.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(
    private val booksNetworkDataSource: IBooksNetworkDataSource,
    private val booksLocalDataSource: IBooksLocalDataSource,
) : IBooksRepository {
    override suspend fun getBooksFromNetwork(entry: String): List<Book> {
        val networkBooks = booksNetworkDataSource.getBooks(entry).convertToDatabaseModel()
        booksLocalDataSource.refreshSavedBooks(networkBooks)
        return booksNetworkDataSource.getBooks(entry).convertToDomainModel()
    }

    override suspend fun getBooksFromDB(): List<Book> {
        return booksLocalDataSource.getBooks()
    }
}