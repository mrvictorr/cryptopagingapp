package com.scalablesolutions.cryptoapp.data.remote.dto

data class DataDto(
    val metrics: MetricsDto,
    val name: String,
    val profile: ProfileDto,
    val symbol: String?
)