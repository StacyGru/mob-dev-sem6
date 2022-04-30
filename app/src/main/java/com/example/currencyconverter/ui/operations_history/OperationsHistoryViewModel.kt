package com.example.currencyconverter.ui.operations_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OperationsHistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "История операций"
    }
    val text: LiveData<String> = _text
}