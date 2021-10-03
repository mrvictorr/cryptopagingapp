package com.scalablesolutions.cryptoapp.presentation.model

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain

data class AssetPresentation (
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val tagline: String?,
    val officialLinks: List<OfficialLinkPresentation>
)

fun AssetDomain.toAssetPresentation(): AssetPresentation{
    return AssetPresentation(
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        tagline = tagline,
        officialLinks = officialLinks.map { officialLinkDomain ->
            officialLinkDomain.toOfficialLinkPresentation()
        }
    )
}