package com.example.currencyconverter.ui.currency_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization

class CurrencyListViewModelFactory(private val repositoryRealization: CurrencyRepositoryRealization): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyListViewModel::class.java)) {
            CurrencyListViewModel(repositoryRealization) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}