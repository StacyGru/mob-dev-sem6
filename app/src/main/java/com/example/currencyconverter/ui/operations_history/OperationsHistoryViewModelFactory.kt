package com.example.currencyconverter.ui.operations_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization

class OperationsHistoryViewModelFactory(private val repositoryRealization: CurrencyRepositoryRealization): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OperationsHistoryViewModel::class.java)) {
            OperationsHistoryViewModel(this.repositoryRealization) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}