package com.example.currencyconverter.ui.currency_exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization

class CurrencyExchangeViewModelFactory(private val repositoryRealization: CurrencyRepositoryRealization): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyExchangeViewModel::class.java)) {
            CurrencyExchangeViewModel(this.repositoryRealization) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}