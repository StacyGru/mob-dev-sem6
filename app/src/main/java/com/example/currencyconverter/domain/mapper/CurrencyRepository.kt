package com.example.currencyconverter.domain.mapper

import com.example.currencyconverter.data.retrofit.repository.RetrofitRepository
import com.example.currencyconverter.domain.model.DataToUse

open class CurrencyRepository(
    private val retrofitRepository: RetrofitRepository

) {

    suspend fun getRetrofitCurrencyList(): DataToUse? {
        return retrofitRepository.getCurrencies()
    }

}