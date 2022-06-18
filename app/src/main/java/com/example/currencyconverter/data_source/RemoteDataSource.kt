package com.example.currencyconverter.data_source

import com.example.currencyconverter.data.retrofit.api.CurrencyApi
import com.example.currencyconverter.domain.model.DataResponse

// вызывает функцию getCurrencies чтобы получить данные с веб-сервиса
class RemoteDataSource(private val currencyApi: CurrencyApi) {

    suspend fun getCurrencies(): DataResponse {
        return currencyApi.getRemoteCurrencyList()
    }
}