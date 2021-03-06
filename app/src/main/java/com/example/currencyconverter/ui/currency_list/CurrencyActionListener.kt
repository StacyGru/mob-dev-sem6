package com.example.currencyconverter.ui.currency_list

import com.example.currencyconverter.domain.model.CurrencyList

interface CurrencyActionListener {
    fun currencyExchange(currency: CurrencyList)

    fun currencyFavorite(currency: CurrencyList)

    fun currencyLongClick(currency: CurrencyList)
}