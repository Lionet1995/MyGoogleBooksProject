package com.example.mygooglebooksproject.data.models

data class BookModel(
    val id: String,
    val selfLink: String,
    val volumeInfo: BookInfoModel
)