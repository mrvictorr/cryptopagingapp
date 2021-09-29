package com.scalablesolutions.cryptoapp.data.remote.dto

data class DataDto(
    val id: String,
    val metrics: MetricsDto,
    val name: String,
    val profile: ProfileDto,
    val slug: String,
    val symbol: String
)