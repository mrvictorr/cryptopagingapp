package com.scalablesolutions.cryptoapp.presentation.model

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain

data class AssetPresentation (
    val name: String,
    val symbol: String,
    var priceUsd: Double,
    val tagline: String?,
    val projectDetails: String?,
    val officialLinks: List<OfficialLinkPresentation>
)

fun AssetDomain.toAssetPresentation(): AssetPresentation{
    return AssetPresentation(
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        tagline = tagline,
        projectDetails = projectDetails,
        officialLinks = officialLinks.map { officialLinkDomain ->
            officialLinkDomain.toOfficialLinkPresentation()
        }
    )
}