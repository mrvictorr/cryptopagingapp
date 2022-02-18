package com.victorr.cryptoapp.data.remote

import com.victorr.cryptoapp.data.remote.dto.AssetsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MessariApi {

    @GET("api/v2/assets")
    suspend fun getAssets(
        @Query("page") page: String,
        @Query("fields", encoded = true) fields: String,
        @Query("limit") limit: String = "20"
    ): AssetsDto
}