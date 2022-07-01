package com.example.currencyconverter.data.retrofit.api

import com.example.currencyconverter.domain.model.DataResponse
import retrofit2.http.GET
// retrofit позволяет получить и загрузить данные через веб-сервис

interface CurrencyApi {
    @GET("api/latest?access_key=6e895dbf7bfbb3721de791f923e8a05c&format=1")
    suspend fun getRemoteCurrencyList(): DataResponse

}