package com.example.mygooglebooksproject.data.storages

import kotlinx.coroutines.flow.Flow

interface IUserSettingsStorage {
    suspend fun saveCount(count: Int)
    val countFlow: Flow<Int>
}