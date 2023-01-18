package com.example.mygooglebooksproject.presentation.di

import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import com.example.mygooglebooksproject.domain.interfaces.IUserSettingsRepository
import com.example.mygooglebooksproject.domain.usecases.GetBooksCountUseCase
import com.example.mygooglebooksproject.domain.usecases.GetLastViewedBooksUseCase
import com.example.mygooglebooksproject.domain.usecases.UpdateBooksCountUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetBooksByUserEntryUseCase(booksRepository: IBooksRepository): GetBooksByUserEntryUseCase {
        return GetBooksByUserEntryUseCase(booksRepository)
    }

    @Provides
    fun provideUpdateBooksCountUseCase(userSettingsRepository: IUserSettingsRepository): UpdateBooksCountUseCase {
        return UpdateBooksCountUseCase(userSettingsRepository)
    }

    @Provides
    fun provideGetBooksCountUseCase(userSettingsRepository: IUserSettingsRepository): GetBooksCountUseCase {
        return GetBooksCountUseCase(userSettingsRepository)
    }

    @Provides
    fun provideGetLastViewedBooksUseCase(booksRepository: IBooksRepository): GetLastViewedBooksUseCase {
        return GetLastViewedBooksUseCase(booksRepository)
    }
}