package com.plcoding.cryptocurrencyappyt.di

import com.victorr.cryptoapp.common.Constants
import com.victorr.cryptoapp.data.datasource.AssetsDataSource
import com.victorr.cryptoapp.data.datasource.AssetsDataSourceImpl
import com.victorr.cryptoapp.data.remote.MessariApi
import com.victorr.cryptoapp.data.repository.AssetsRepositoryImpl
import com.victorr.cryptoapp.domain.repository.AssetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


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