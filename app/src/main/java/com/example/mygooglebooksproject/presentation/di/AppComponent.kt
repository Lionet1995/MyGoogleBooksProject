package com.example.mygooglebooksproject.presentation.di

import com.example.mygooglebooksproject.presentation.adapters.BooksListAdapter
import com.example.mygooglebooksproject.presentation.fragments.BooksListFragment
import com.example.mygooglebooksproject.presentation.fragments.SettingsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(booksListFragment: BooksListFragment)
}