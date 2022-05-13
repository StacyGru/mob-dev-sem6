package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data_source.LocalDataSource
import com.example.currencyconverter.data_source.RemoteDataSource
import com.example.currencyconverter.domain.mapper.CurrencyDtoMapper
import com.example.currencyconverter.domain.model.Currencies

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCurrencies(): Currencies? {
        // проверка данных на "свежесть" (5 минут)
        // сравнить значение

        // вызываем функцию getCurrencies и конвертируем данные в более удобный для работы вид

        return try {
            val response = remoteDataSource.getCurrencies()
            CurrencyDtoMapper.mapResponseToDomainModel(response)
        }
        catch (e: Exception) {
            null
        }
    }
}