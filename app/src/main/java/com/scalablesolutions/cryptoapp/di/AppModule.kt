package com.plcoding.cryptocurrencyappyt.di

import com.scalablesolutions.cryptoapp.common.Constants
import com.scalablesolutions.cryptoapp.data.datasource.AssetsDataSource
import com.scalablesolutions.cryptoapp.data.datasource.AssetsDataSourceImpl
import com.scalablesolutions.cryptoapp.data.remote.MessariApi
import com.scalablesolutions.cryptoapp.data.repository.AssetsRepositoryImpl
import com.scalablesolutions.cryptoapp.domain.repository.AssetsRepository
import com.scalablesolutions.cryptoapp.domain.usecase.GetAssetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMessariApi(): MessariApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MessariApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAssetsDataSource(api: MessariApi): AssetsDataSource {
        return AssetsDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideAssetsRepository(assetsDataSource: AssetsDataSource): AssetsRepository {
        return AssetsRepositoryImpl(assetsDataSource)
    }

}