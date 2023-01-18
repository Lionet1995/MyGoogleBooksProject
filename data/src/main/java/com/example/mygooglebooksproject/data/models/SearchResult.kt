package com.example.mygooglebooksproject.data.models

import com.example.mygooglebooksproject.domain.models.Book

data class SearchResult(val items: List<BookModel>)

fun SearchResult.convertToDomainModel(): List<Book> {
    return items.map {
        Book(
            title = it.volumeInfo.title,
            description = it.volumeInfo.description,
            source = it.volumeInfo.imageLinks?.smallThumbnail
        )
    }
}

fun SearchResult.convertToDatabaseModel(): List<RoomBookModel> {
    return items.map {
        RoomBookModel(
            id = it.id,
            title = it.volumeInfo.title,
            description = it.volumeInfo.description,
            source = it.volumeInfo.imageLinks?.smallThumbnail
        )
    }
}