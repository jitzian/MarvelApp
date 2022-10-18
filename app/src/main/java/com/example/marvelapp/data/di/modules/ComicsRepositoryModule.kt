package com.example.marvelapp.data.di.modules

import com.example.marvelapp.data.repository.comics.ComicsRepositoryImpl
import com.example.marvelapp.domain.repository.comics.ComicsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ComicsRepositoryModule {

    @Binds
    @Singleton
    abstract fun comicsRepository(
        comicsRepository: ComicsRepositoryImpl
    ): ComicsRepository

}