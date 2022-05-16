package com.example.currencyconverter.domain.model

import java.util.*

// здесь описываем то, в каком формате нам УДОБНО хранить и обрабатывать полученные через retrofit данные
data class DataToUse(
    val base: String,
    val date: String,
    val rates: List<CurrencyList>
)

