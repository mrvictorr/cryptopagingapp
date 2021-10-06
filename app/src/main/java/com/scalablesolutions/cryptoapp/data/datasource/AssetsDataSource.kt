package com.scalablesolutions.cryptoapp.data.datasource

import com.scalablesolutions.cryptoapp.data.remote.dto.DataDto
import kotlinx.coroutines.flow.Flow

interface AssetsDataSource {
    suspend fun getAssets(page: Int): Collection<DataDto>
    fun getAssetBySymbol(symbol: String): DataDto?
    suspend fun updatePriceOfAssets(): Flow<Collection<DataDto>>
}