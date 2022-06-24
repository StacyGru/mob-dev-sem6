package com.example.currencyconverter.ui.currency_exchange

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization
import com.example.currencyconverter.domain.model.CurrencyList
import kotlinx.coroutines.runBlocking

class CurrencyExchangeViewModel(private var realization: CurrencyRepositoryRealization) : ViewModel() {

    fun getFavoriteCurrencyList(): MutableList<CurrencyList>? {
        return runBlocking {
            realization.getFavoriteCurrencyList()
        }
    }

    fun getUSD(): CurrencyList {
        return runBlocking {
            realization.getUSD()
        }
    }

    fun getRUB(): CurrencyList {
        return runBlocking {
            realization.getRUB()
        }
    }
}