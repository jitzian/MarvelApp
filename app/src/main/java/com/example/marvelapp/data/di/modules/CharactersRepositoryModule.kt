package com.example.marvelapp.data.di.modules

import com.example.marvelapp.data.repository.characters.CharactersRepositoryImpl
import com.example.marvelapp.domain.repository.characters.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    @Singleton
    abstract fun charactersRepository(
        charactersRepository: CharactersRepositoryImpl
    ): CharactersRepository

}