package com.example.mygooglebooksproject.data.models

data class BookInfoModel (
    val title: String,
    val description: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val imageLinks: ImageLinks?
    )