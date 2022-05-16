package com.example.currencyconverter.data.api

import com.example.currencyconverter.domain.model.DataResponse
import retrofit2.http.GET
// retrofit позволяет получить и загрузить данные через веб-сервис

interface CurrencyApi {
    @GET("api/latest?access_key=75e7c0c07846011ba35de8c6e2d73e57&format=1")
    suspend fun getCurrencies(): DataResponse

}