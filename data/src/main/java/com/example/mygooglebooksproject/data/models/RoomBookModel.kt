package com.example.mygooglebooksproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mygooglebooksproject.domain.models.Book

@Entity(tableName = "books")
data class RoomBookModel (
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String? = "",
    val source: String? = ""
    )


fun List<RoomBookModel>.convertToDomainModel(): List<Book> {
    return map {
        Book(
            title = it.title,
            description = it.description,
            source = it.source
        )
    }
}