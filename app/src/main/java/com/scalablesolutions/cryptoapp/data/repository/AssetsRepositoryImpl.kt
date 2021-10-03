package com.scalablesolutions.cryptoapp.data.repository

import com.scalablesolutions.cryptoapp.common.Constants
import com.scalablesolutions.cryptoapp.data.datasource.AssetsDataSource
import com.scalablesolutions.cryptoapp.data.remote.MessariApi
import com.scalablesolutions.cryptoapp.data.remote.dto.toAssetsDomain
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.repository.AssetsRepository
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val dataSource: AssetsDataSource
): AssetsRepository {

    override suspend fun getAssets(): List<AssetDomain> {
        return dataSource.getAssets().toAssetsDomain()
    }

}