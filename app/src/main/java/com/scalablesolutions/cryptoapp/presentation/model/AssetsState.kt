package com.scalablesolutions.cryptoapp.presentation.model

import androidx.paging.PagingData
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import kotlinx.coroutines.flow.Flow

data class AssetsState(
    val assets: Flow<PagingData<AssetPresentation>>? = null
)
