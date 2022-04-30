package com.example.currencyconverter.ui.currency_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrencyListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Список валют"
    }
    val text: LiveData<String> = _text
}