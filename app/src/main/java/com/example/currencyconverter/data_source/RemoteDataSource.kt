package com.example.currencyconverter.data_source

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data.CurrencyResponse

// вызывает функцию getCurrencies чтобы получить данные с веб-сервиса
class RemoteDataSource(private val currencyApi: CurrencyApi) {

    suspend fun getCurrencies(): CurrencyResponse {
        return currencyApi.getCurrencies()
    }
}