package com.example.mygooglebooksproject.presentation.di

import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetBooksByUserEntryUseCase(booksRepository: IBooksRepository): GetBooksByUserEntryUseCase {
        return GetBooksByUserEntryUseCase(booksRepository)
    }
}