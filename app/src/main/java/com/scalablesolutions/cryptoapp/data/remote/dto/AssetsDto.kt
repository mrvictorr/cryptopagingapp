package com.scalablesolutions.cryptoapp.data.remote.dto

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain


data class AssetsDto(
    val data: List<DataDto>,
    val status: StatusDto
)

fun Collection<DataDto>.toAssetsDomain(): List<AssetDomain>{
    return this.map { dataDto ->
        AssetDomain(
            name = dataDto.name,
            symbol = dataDto.symbol ?: "",
            priceUsd = dataDto.metrics.marketData.priceUsd,
            tagline = dataDto.profile.general.overview.tagline,
            officialLinks = dataDto.profile.general.overview.officialLinks?.map { officialLinkDto ->
                officialLinkDto.toOfficialLinkDomain()
            } ?: emptyList()
        )
    }
}