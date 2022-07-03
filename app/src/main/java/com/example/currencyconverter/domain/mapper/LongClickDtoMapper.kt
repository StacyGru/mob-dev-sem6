package com.example.currencyconverter.domain.mapper

import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.domain.model.LongClick

object LongClickDtoMapper {
    fun fromCurrencyListToLongClick(currency: CurrencyList): LongClick {
        return LongClick(
            id = 1,
            name = currency.name,
            value = currency.value,
        )
    }
    fun fromLongClickToCurrencyList(currency: LongClick): CurrencyList {
        return CurrencyList(
            name = currency.name,
            value = currency.value,
            is_favorite = false
        )
    }
}