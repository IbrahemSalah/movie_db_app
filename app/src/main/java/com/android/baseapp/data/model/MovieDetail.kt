package com.android.baseapp.data.model

data class MovieDetail(
	val originalLanguage: String? = null,
	val imdbId: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val backdrop_path: String? = null,
	val revenue: Int? = null,
	val genres: List<GenresItem?>? = null,
	val popularity: Double? = null,
	val productionCountries: List<ProductionCountriesItem?>? = null,
	val id: Int? = null,
	val voteCount: Int? = null,
	val budget: Int? = null,
	val overview: String? = null,
	val originalTitle: String? = null,
	val runtime: Int? = null,
	val poster_path: String? = null,
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,
	val productionCompanies: List<ProductionCompaniesItem?>? = null,
	val releaseDate: String? = null,
	val voteAverage: Double? = null,
	val belongsToCollection: BelongsToCollection? = null,
	val tagline: String? = null,
	val adult: Boolean? = null,
	val homepage: String? = null,
	val status: String? = null
)

data class ProductionCompaniesItem(
	val logoPath: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val originCountry: String? = null
)

data class ProductionCountriesItem(
	val iso31661: String? = null,
	val name: String? = null
)

data class BelongsToCollection(
	val backdropPath: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val posterPath: String? = null
)

data class GenresItem(
	val name: String? = null,
	val id: Int? = null
)

data class SpokenLanguagesItem(
	val name: String? = null,
	val iso6391: String? = null,
	val englishName: String? = null
)

