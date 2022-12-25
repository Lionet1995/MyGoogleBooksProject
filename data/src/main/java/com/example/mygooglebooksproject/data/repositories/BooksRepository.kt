package com.example.mygooglebooksproject.data.repositories

import com.example.mygooglebooksproject.data.models.convertToDomainModel
import com.example.mygooglebooksproject.data.source.IBooksNetworkDataSource
import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import com.example.mygooglebooksproject.domain.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(
    private val booksNetworkDataSource: IBooksNetworkDataSource
) : IBooksRepository {
    override suspend fun getBooks(entry: String): List<Book> {
        val result = withContext(Dispatchers.IO) {
            booksNetworkDataSource.getBooks(entry).convertToDomainModel()
        }
        return result
    }

}