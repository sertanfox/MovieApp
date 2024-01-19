package com.feature.movie_details.data.mapper

import com.core.network.ApiConstants
import com.core.network.model.movie_details.MovieDetailsDto
import com.feature.movie_details.domain.model.MovieDetails

fun MovieDetailsDto.toDomain():MovieDetails {
    return MovieDetails(
        title = this.originalTitle,
        description = this.overview,
        imageUrl = makeFullUrl(this.posterPath)
    )
}

fun makeFullUrl(path:String) = "${ApiConstants.IMAGE_BASE_URL}$path"