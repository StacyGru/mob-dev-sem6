package com.example.currencyconverter.ui.currency_exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization
import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.domain.model.LongClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun getExchangeHistory(): MutableList<CurrencyExchange> {
        return runBlocking {
            realization.getExchangeHistory()
        }
    }

    fun addExchange(exchange: CurrencyExchange, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.addExchange(exchange){
                onSuccess()
            }
        }
    }

    fun getLongClick(): LongClick{
        return runBlocking {
            realization.getLongClick()
        }
    }
}