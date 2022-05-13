package com.example.currencyconverter.domain.mapper

import com.example.currencyconverter.data.CurrencyResponse
import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.domain.model.Currency
import java.text.SimpleDateFormat

// здесь конвертируем данные из формата CurrencyResponse.kt в формат Currencies.kt
// т.е. из формата, в котором мы получили данные с веб-сервиса в формат, с которым нам будет удобнее работать
object CurrencyDtoMapper {

    fun mapResponseToDomainModel(response: CurrencyResponse): Currencies {
        return Currencies(
            date = SimpleDateFormat("yyyy-MM-dd").parse(response.date),
            base = response.base,
            rates = response.rates.toList().map {
                Currency(it.first, it.second)
            }
        )
    }
}