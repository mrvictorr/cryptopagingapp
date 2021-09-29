package com.scalablesolutions.cryptoapp.data.remote.dto

data class OhlcvLast1HourDto(
    val close: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val volume: Double
)