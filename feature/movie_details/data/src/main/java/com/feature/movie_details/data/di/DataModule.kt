package com.feature.movie_details.data.di

import com.core.dataproviders.MovieDataProviders
import com.feature.movie_details.data.repository.MovieDetailsRepoImpl
import com.feature.movie_details.domain.repository.MovieDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideMovieDetailsRepo(movieDataProviders: MovieDataProviders):MovieDetailsRepo {
        return MovieDetailsRepoImpl(movieDataProviders)
    }
}