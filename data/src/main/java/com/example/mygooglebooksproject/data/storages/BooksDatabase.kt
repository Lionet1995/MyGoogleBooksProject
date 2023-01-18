package com.example.mygooglebooksproject.data.storages

import android.content.Context
import androidx.room.*
import com.example.mygooglebooksproject.data.api.BooksDao
import com.example.mygooglebooksproject.data.models.RoomBookModel

@Database(entities = [RoomBookModel:: class], version = 1)
abstract class BooksDatabase: RoomDatabase() {
    abstract fun booksDao(): BooksDao
}

private lateinit var INSTANCE: BooksDatabase

fun getBooksDatabase(context: Context): BooksDatabase {
    synchronized(BooksDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BooksDatabase::class.java,
                "books"
            ).build()
        }
    }
    return INSTANCE
}