package com.example.mygooglebooksproject.domain.usecases

import com.example.mygooglebooksproject.domain.interfaces.IUserSettingsRepository

class UpdateBooksCountUseCase(private val userSettings: IUserSettingsRepository) {
    suspend fun execute(count: Int) {
        userSettings.updateBooksListCount(count)
    }
}