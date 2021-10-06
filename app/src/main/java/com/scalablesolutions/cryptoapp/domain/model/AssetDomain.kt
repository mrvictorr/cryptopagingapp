package com.scalablesolutions.cryptoapp.domain.model


data class AssetDomain (
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val tagline: String?,
    val projectDetails: String?,
    val officialLinks: List<OfficialLinkDomain>
)