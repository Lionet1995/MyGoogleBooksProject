package com.example.mygooglebooksproject.data.repositories

import com.example.mygooglebooksproject.data.storages.IUserSettingsStorage
import com.example.mygooglebooksproject.domain.interfaces.IUserSettingsRepository
import kotlinx.coroutines.flow.Flow

class UserSettingsRepository( private val userSettingsStorage: IUserSettingsStorage)
    : IUserSettingsRepository {

    override suspend fun updateBooksListCount(count: Int) {
        userSettingsStorage.saveCount(count)
    }

    override fun getBooksListCount(): Flow<Int> {
        return userSettingsStorage.countFlow
    }
}