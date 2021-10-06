package com.scalablesolutions.cryptoapp.presentation.model

data class UpdatePriceState(
    val assets: List<AssetPresentation> = emptyList()
)