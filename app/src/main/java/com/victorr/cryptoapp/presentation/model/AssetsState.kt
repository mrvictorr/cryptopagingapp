package com.victorr.cryptoapp.presentation.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class AssetsState(
    val assets: Flow<PagingData<AssetPresentation>>? = null
)
