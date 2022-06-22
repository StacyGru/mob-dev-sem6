package com.example.currencyconverter.ui.currency_list

import com.example.currencyconverter.domain.model.CurrencyList

interface CurrencyActionListener {
    fun currencyExchange(currency: CurrencyList)

    fun onCurrencyFavorite(currency: CurrencyList)

    fun currencyUp(currency: CurrencyList)
}