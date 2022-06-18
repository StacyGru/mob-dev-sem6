package com.example.currencyconverter.data.retrofit.repository

import com.example.currencyconverter.data.retrofit.api.RetrofitInstance
import com.example.currencyconverter.data_source.LocalDataSource
import com.example.currencyconverter.data_source.RemoteDataSource
import com.example.currencyconverter.domain.mapper.CurrencyDtoMapper
import com.example.currencyconverter.domain.model.DataToUse

class RetrofitRepository(
//    private val localDataSource: LocalDataSource,
//    private val remoteDataSource: RemoteDataSource
//    private val remoteDataSource: RemoteDataSource

) {
    suspend fun getCurrencies(): DataToUse? {
        // проверка данных на "свежесть" (5 минут)
        // сравнить значение

        // вызываем функцию getCurrencies и конвертируем данные в более удобный для работы вид

        return try {
            val response = RetrofitInstance.service.getRemoteCurrencyList()
            CurrencyDtoMapper.mapResponseToDomainModel(response)
        }
        catch (e: Exception) {
            null
        }
    }
}