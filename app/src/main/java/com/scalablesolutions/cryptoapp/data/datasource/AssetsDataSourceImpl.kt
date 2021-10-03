package com.scalablesolutions.cryptoapp.data.datasource

import com.scalablesolutions.cryptoapp.common.Constants
import com.scalablesolutions.cryptoapp.data.remote.MessariApi
import com.scalablesolutions.cryptoapp.data.remote.dto.AssetsDto
import com.scalablesolutions.cryptoapp.data.remote.dto.DataDto
import com.scalablesolutions.cryptoapp.data.remote.dto.toAssetsDomain
import javax.inject.Inject

class AssetsDataSourceImpl @Inject constructor(
    private val api: MessariApi
): AssetsDataSource {

    private var assetsList: Collection<DataDto> = mutableListOf()

    override suspend fun getAssets(): Collection<DataDto> {
        return if (assetsList.size%20 == 0) {
            val currentPage = assetsList.size/20 + 1
            val assetsDto = api.getAssets(currentPage.toString(), Constants.REQUEST_FIELDS)
            (assetsList as MutableList).addAll(assetsDto.data)
            assetsList
        }else {
            assetsList
        }
    }


}