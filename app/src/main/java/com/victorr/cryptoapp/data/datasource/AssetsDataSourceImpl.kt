package com.victorr.cryptoapp.data.datasource

import com.victorr.cryptoapp.common.Constants
import com.victorr.cryptoapp.data.remote.MessariApi
import com.victorr.cryptoapp.data.remote.dto.DataDto
import javax.inject.Inject

class AssetsDataSourceImpl @Inject constructor(
    private val api: MessariApi
): AssetsDataSource {

    //cache
    private var assetsList: Collection<DataDto> = mutableListOf()

    override suspend fun getAssets(page: Int): Collection<DataDto> {
        return if (assetsList.size % 20 == 0) {
            if (page * 20 > assetsList.size) {
                val currentPage = assetsList.size / 20 + 1
                val assetsDto = api.getAssets(currentPage.toString(), Constants.GET_ASSETS_FIELDS)
                (assetsList as MutableList).addAll(assetsDto.data)
                assetsDto.data
            } else {
                val list = (assetsList as MutableList).slice((page - 1) * 20 until page * 20)
                list
            }
        } else {
            emptyList()
        }
    }

}