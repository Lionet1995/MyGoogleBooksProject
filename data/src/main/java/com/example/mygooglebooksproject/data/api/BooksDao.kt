package com.example.mygooglebooksproject.data.api

import android.util.Log
import androidx.room.*
import com.example.mygooglebooksproject.data.models.RoomBookModel

@Dao
interface BooksDao {
    @Query("SELECT * FROM books")
    suspend fun loadBooks(): List<RoomBookModel>

    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<RoomBookModel>)

    @Transaction
    suspend fun refreshBooks(books: List<RoomBookModel>) {
        deleteAllBooks()
        insertBooks(books)
    }

}
