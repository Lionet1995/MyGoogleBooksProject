package com.example.mygooglebooksproject.presentation.di

import android.content.Context
import com.example.mygooglebooksproject.data.api.BooksService
import com.example.mygooglebooksproject.data.repositories.BooksRepository
import com.example.mygooglebooksproject.data.repositories.UserSettingsRepository
import com.example.mygooglebooksproject.data.source.BooksNetworkDataSource
import com.example.mygooglebooksproject.data.source.IBooksNetworkDataSource
import com.example.mygooglebooksproject.data.storages.IUserSettingsStorage
import com.example.mygooglebooksproject.data.storages.DataStorePrefUserSettingsStorage
import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import com.example.mygooglebooksproject.domain.interfaces.IUserSettingsRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideBooksNetworkDataSource(booksService: BooksService): IBooksNetworkDataSource {
        return BooksNetworkDataSource(booksService)
    }


    @Provides
    @Singleton
    fun provideBooksRepository(
        booksNetworkDataSource: BooksNetworkDataSource
    ): IBooksRepository {
        return BooksRepository(
            booksNetworkDataSource = booksNetworkDataSource
        )
    }

    @Provides
    fun provideUserSettingsStorage(context: Context): IUserSettingsStorage {
        return DataStorePrefUserSettingsStorage(context = context)
    }


    @Provides
    @Singleton
    fun provideUserSettingsRepository(
        userSettingsStorage: IUserSettingsStorage
    ): IUserSettingsRepository {
        return UserSettingsRepository(
            userSettingsStorage = userSettingsStorage
        )
    }
}