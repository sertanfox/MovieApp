package com.feature.movie.data.di

import com.core.dataproviders.MovieDataProviders
import com.feature.movie.data.repo.MovieRepoImpl
import com.feature.movie.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataLayerModule {

    @Provides
    fun provideMovieRepo(movieDataProviders: MovieDataProviders):MovieRepository {
        return MovieRepoImpl(movieDataProviders)
    }
}