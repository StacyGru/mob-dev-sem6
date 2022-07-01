package com.example.currencyconverter.data.room.repository

import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.domain.model.CurrencyList

interface LocalCurrencyRepository {

    suspend fun insertCurrencyList(currency: CurrencyList, onSuccess:() -> Unit)
    suspend fun getRoomCurrencyList(): MutableList<CurrencyList>
    suspend fun updateListCurrency(currency: CurrencyList, onSuccess: () -> Unit)
    suspend fun updateListFavoriteCurrency(currency: CurrencyList, onSuccess: () -> Unit)
    suspend fun getFavoriteCurrencyList(): MutableList<CurrencyList>?
    suspend fun getUSD(): CurrencyList
    suspend fun getRUB(): CurrencyList
    suspend fun getExchangeHistory(): MutableList<CurrencyExchange>
    suspend fun addExchange(exchange: CurrencyExchange, onSuccess: () -> Unit)
}