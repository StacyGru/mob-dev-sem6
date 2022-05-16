package com.example.currencyconverter.data.api

import com.example.currencyconverter.data_source.LocalDataSource
import com.example.currencyconverter.data_source.RemoteDataSource
import com.example.currencyconverter.data.repository.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// реализация retrofit и связь с репозиторием
object DependencyInjection {
    private val interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://data.fixer.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CurrencyApi::class.java)

    private val localDataSource = LocalDataSource()
    private val remoteDataSource = RemoteDataSource(service)

    val repository = Repository(localDataSource, remoteDataSource)
}