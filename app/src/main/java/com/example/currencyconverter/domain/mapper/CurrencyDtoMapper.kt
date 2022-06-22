package com.example.currencyconverter.domain.mapper

import com.example.currencyconverter.domain.model.*

// здесь конвертируем данные из формата CurrencyResponse.kt в формат Currencies.kt
// т.е. из формата, в котором мы получили данные с веб-сервиса в формат, с которым нам будет удобнее работать
object CurrencyDtoMapper {

    fun mapResponseToDomainModel(response: DataResponse): DataToUse {
        return DataToUse(
            date = response.date,
            base = response.base,
            rates = response.rates.toList().map {
                CurrencyList(it.first, it.second, false)
            })
    }
}