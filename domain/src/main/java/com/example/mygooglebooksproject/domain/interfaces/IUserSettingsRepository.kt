package com.example.mygooglebooksproject.domain.interfaces

import kotlinx.coroutines.flow.Flow

interface IUserSettingsRepository {
    suspend fun updateBooksListCount(count: Int)
    fun getBooksListCount(): Flow<Int>
}