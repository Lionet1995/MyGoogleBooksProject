package com.example.mygooglebooksproject.domain.usecases

import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import com.example.mygooglebooksproject.domain.models.Book

class GetLastViewedBooksUseCase(private val booksRepository: IBooksRepository) {
    suspend fun execute(): List<Book> {
        return booksRepository.getBooksFromDB()
    }
}