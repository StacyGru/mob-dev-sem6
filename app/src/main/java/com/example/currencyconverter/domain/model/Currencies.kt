package com.example.currencyconverter.domain.model

import java.util.*

// здесь описываем то, в каком формате нам УДОБНО хранить и обрабатывать полученные через retrofit данные
data class Currencies(
    val base: String,
    val date: Date,
    val rates: List<Currency>
)

data class Currency (
//    val id: Int,
    val name: String,
    val value: Double
)