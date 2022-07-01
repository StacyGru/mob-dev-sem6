package com.example.currencyconverter.data.room.repository

import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.domain.model.CurrencyList

class CurrencyRepositoryRealization(private val currencyDao: CurrencyDao): LocalCurrencyRepository {

    override suspend fun insertCurrencyList(currency: CurrencyList, onSuccess: () -> Unit) {
        currencyDao.insertCurrencyList(currency)
        onSuccess()
    }

    override suspend fun getRoomCurrencyList(): MutableList<CurrencyList>{
        return currencyDao.getRoomCurrencyList()
    }

    override suspend fun updateListCurrency(currency: CurrencyList, onSuccess: () -> Unit) {
        currencyDao.updateListCurrency(currency.name,  currency.value)
        onSuccess()
    }

    override suspend fun updateListFavoriteCurrency(currency: CurrencyList, onSuccess: () -> Unit) {
        currencyDao.updateListFavoriteCurrency(currency.name,  currency.is_favorite)
        onSuccess()
    }

    override suspend fun getFavoriteCurrencyList(): MutableList<CurrencyList>? {
        return currencyDao.getFavoriteCurrencyList()
    }

    override suspend fun getUSD(): CurrencyList {
        return currencyDao.getUSD()
    }

    override suspend fun getRUB(): CurrencyList {
        return currencyDao.getRUB()
    }

    override suspend fun getExchangeHistory(): MutableList<CurrencyExchange> {
        return currencyDao.getExchangeHistory()
    }

    override suspend fun addExchange(exchange: CurrencyExchange, onSuccess: () -> Unit) {
        currencyDao.addExchange(exchange)
        onSuccess()
    }
}