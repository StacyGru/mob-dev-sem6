package com.example.currencyconverter.ui.currency_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.retrofit.api.RetrofitInstance.repository
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization
import com.example.currencyconverter.domain.mapper.LongClickDtoMapper
import com.example.currencyconverter.domain.model.CurrencyList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CurrencyListViewModel(private var realization: CurrencyRepositoryRealization) : ViewModel() {

    val liveData = MutableLiveData<List<CurrencyList>>()

    fun init() {    // если БД уже есть
        this.getCurrencyList()
    }

//    fun init() {    // если БД нет
//        this.getRetrofitCurrencyList()
//    }

    fun getRetrofitCurrencyList(){
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(getLocalCurrencyList())
            repository.getCurrencies()?.let { remoteCurrencies ->
                remoteCurrencies.rates.map { remoteCurrency ->
                    insertCurrencyList(remoteCurrency) {}
                }
                liveData.postValue(getLocalCurrencyList())
            }
        }
    }

    private fun getLocalCurrencyList(): MutableList<CurrencyList>{
        return runBlocking {
            realization.getRoomCurrencyList()
        }
    }

    fun insertCurrencyList(currency: CurrencyList, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.insertCurrencyList(currency) {
                onSuccess()
            }
        }
    }

    private fun getCurrencyList(){
        return liveData.postValue(getLocalCurrencyList())
    }

    fun updateListCurrency(currency: CurrencyList, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateListCurrency(currency){
                onSuccess()
            }
        }
    }

    fun updateListFavoriteCurrency(currency: CurrencyList, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateListFavoriteCurrency(currency){
                val updated = getLocalCurrencyList()
                liveData.postValue(updated)
                onSuccess()
            }
        }
    }

    fun longClickExchange(currency: CurrencyList){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateLongClick(LongClickDtoMapper.fromCurrencyListToLongClick(currency))   // если в БД уже есть запись
//            realization.insertLongClick(LongClickDtoMapper.fromCurrencyListToLongClick(currency))   // если записи нет
        }
    }
}