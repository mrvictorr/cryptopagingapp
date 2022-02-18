package com.victorr.cryptoapp.data.repository

import com.victorr.cryptoapp.data.datasource.AssetsDataSource
import com.victorr.cryptoapp.data.remote.dto.toAssetsDomain
import com.victorr.cryptoapp.domain.model.AssetDomain
import com.victorr.cryptoapp.domain.repository.AssetsRepository
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val dataSource: AssetsDataSource
): AssetsRepository {

    override suspend fun getAssets(page: Int): List<AssetDomain> {
        return dataSource.getAssets(page).toAssetsDomain()
    }

}