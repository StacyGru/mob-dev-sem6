package com.example.currencyconverter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.currencyconverter.domain.model.CurrencyList

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencyList(currency: CurrencyList)

    @Query("SELECT * FROM currencies")
    suspend fun getRoomCurrencyList(): MutableList<CurrencyList>

    @Query("UPDATE currencies SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)
}