package com.example.mygooglebooksproject.domain.usecases

import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import com.example.mygooglebooksproject.domain.models.Book

class GetBooksByUserEntryUseCase(private val booksRepository: IBooksRepository) {
    suspend fun execute(entry: String): List<Book> {
        return booksRepository.getBooks(entry)
    }
}