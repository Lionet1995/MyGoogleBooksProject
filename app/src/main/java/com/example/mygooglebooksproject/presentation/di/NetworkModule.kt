package com.example.mygooglebooksproject.presentation.di

import com.example.mygooglebooksproject.data.api.BooksService
import com.example.mygooglebooksproject.data.utils.Configs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksService(moshi: Moshi): BooksService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Configs.BASE_URL)
            .build()
        return retrofit.create(BooksService::class.java)

    }
}