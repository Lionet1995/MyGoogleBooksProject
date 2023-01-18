package com.example.mygooglebooksproject.presentation.di

import android.content.Context
import com.example.mygooglebooksproject.data.api.BooksService
import com.example.mygooglebooksproject.data.repositories.BooksRepository
import com.example.mygooglebooksproject.data.repositories.UserSettingsRepository
import com.example.mygooglebooksproject.data.source.local.BooksLocalDataSource
import com.example.mygooglebooksproject.data.source.local.IBooksLocalDataSource
import com.example.mygooglebooksproject.data.source.remote.BooksNetworkDataSource
import com.example.mygooglebooksproject.data.source.remote.IBooksNetworkDataSource
import com.example.mygooglebooksproject.data.storages.BooksDatabase
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
    fun provideBooksLocalDataSource(booksDatabase: BooksDatabase): IBooksLocalDataSource {
        return BooksLocalDataSource(booksDatabase)
    }

    @Provides
    @Singleton
    fun provideBooksRepository(
        booksNetworkDataSource: BooksNetworkDataSource,
        booksLocalDataSource: BooksLocalDataSource
    ): IBooksRepository {
        return BooksRepository(
            booksNetworkDataSource = booksNetworkDataSource,
            booksLocalDataSource = booksLocalDataSource
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