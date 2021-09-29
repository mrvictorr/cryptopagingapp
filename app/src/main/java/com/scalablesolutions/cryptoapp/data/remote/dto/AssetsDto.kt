package com.scalablesolutions.cryptoapp.data.remote.dto

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.model.OfficialLinkDomain


data class AssetsDto(
    val data: List<DataDto>,
    val status: StatusDto
)

fun AssetsDto.toAssetsDomain(): List<AssetDomain>{
    return this.data.map { dataDto ->
        AssetDomain(
            name = dataDto.name,
            symbol = dataDto.symbol,
            priceUsd = dataDto.metrics.marketData.priceUsd,
            tagline = dataDto.profile.profile.general.overview.tagline,
            officialLinks = dataDto.profile.profile.general.overview.officialLinks.map { officialLinkDto ->
                officialLinkDto.toOfficialLinkDomain()
            }
        )
    }
}