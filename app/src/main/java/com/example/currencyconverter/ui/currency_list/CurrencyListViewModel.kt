package com.example.currencyconverter.ui.currency_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.api.DependencyInjection.repository
import com.example.currencyconverter.data.repository.Repository
import com.example.currencyconverter.domain.model.DataToUse
import kotlinx.coroutines.launch
import retrofit2.Response

class CurrencyListViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "Список валют"
//    }
//    val text: LiveData<String> = _text


    val currencyList: MutableLiveData<DataToUse> = MutableLiveData()

    fun getCurrencyList(){
        viewModelScope.launch {
            currencyList.value = repository.getCurrencies()
        }
    }
}