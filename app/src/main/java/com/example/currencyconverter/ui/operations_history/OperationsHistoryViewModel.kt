package com.example.currencyconverter.ui.operations_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization
import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.domain.model.DataToUse
import kotlinx.coroutines.runBlocking

class OperationsHistoryViewModel(private val realization: CurrencyRepositoryRealization) : ViewModel() {

    val data: MutableLiveData<DataToUse> by lazy {
        MutableLiveData()
    }
    fun getExchangeHistory(): MutableList<CurrencyExchange> {
        return runBlocking {
            realization.getExchangeHistory()
        }
    }
}