package com.example.currencyconverter.data.room.repository

import com.example.currencyconverter.data.room.dao.CurrencyDao
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
}