package com.scalablesolutions.cryptoapp.data.repository

import com.scalablesolutions.cryptoapp.data.datasource.AssetsDataSource
import com.scalablesolutions.cryptoapp.data.remote.dto.toAssetsDomain
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val dataSource: AssetsDataSource
): AssetsRepository {

    override suspend fun getAssets(): List<AssetDomain> {
        return dataSource.getAssets().toAssetsDomain()
    }

    override suspend fun updatePriceOfAssets(): Flow<List<AssetDomain>> {
        return dataSource.updatePriceOfAssets().map { dataList ->
            dataList.toAssetsDomain()
        }
    }

}