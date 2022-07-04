package com.example.currencyconverter.ui.operations_history

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.room.repository.CurrencyRepositoryRealization
import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.domain.model.DataToUse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class OperationsHistoryViewModel(private val realization: CurrencyRepositoryRealization) : ViewModel() {

    val data: MutableLiveData<MutableList<CurrencyExchange>> by lazy {
        MutableLiveData()
    }

    val filter: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    private val lastMonthExchangeHistory: MutableList<CurrencyExchange> = mutableListOf()

    fun getExchangeHistory(): MutableList<CurrencyExchange> {
        return runBlocking {
            realization.getExchangeHistory()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLastMonthExchangeHistory(): List<CurrencyExchange> {
        return runBlocking {
            realization.getExchangeHistory().filter {
                checkMonth(it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    private fun checkMonth(currencyExchange: CurrencyExchange): Boolean {
        return try {
            val date = LocalDateTime.parse(
                currencyExchange.date,
                DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm", Locale.ENGLISH)
            )
            date.month == LocalDate.now().month
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("MY_TAG_ERROR", e.localizedMessage)
            false
        }
    }

}