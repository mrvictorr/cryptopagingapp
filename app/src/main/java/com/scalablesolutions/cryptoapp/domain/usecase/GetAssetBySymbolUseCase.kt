package com.scalablesolutions.cryptoapp.domain.usecase

import com.scalablesolutions.cryptoapp.common.Resource
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAssetBySymbolUseCase @Inject constructor(
    private val repository: AssetsRepository
) {
    operator fun invoke(symbol: String): Flow<Resource<AssetDomain>> = flow {
        val asset = repository.getAssetBySymbol(symbol)
        if (asset == null) {
            emit(Resource.Error<AssetDomain>("Asset not found"))
        } else {
            emit(Resource.Success<AssetDomain>(asset))
        }
    }
}