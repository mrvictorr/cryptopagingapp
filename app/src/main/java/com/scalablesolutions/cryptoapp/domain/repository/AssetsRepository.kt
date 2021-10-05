package com.scalablesolutions.cryptoapp.domain.repository

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import kotlinx.coroutines.flow.Flow

interface AssetsRepository {
    suspend fun getAssets(): List<AssetDomain>
    suspend fun updatePriceOfAssets(): Flow<List<AssetDomain>>
}