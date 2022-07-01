package com.example.currencyconverter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.domain.model.CurrencyList

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencyList(currency: CurrencyList)

    @Query("SELECT * FROM currencies ORDER BY is_favorite DESC")
    suspend fun getRoomCurrencyList(): MutableList<CurrencyList>

    @Query("UPDATE currencies SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)

    @Query("UPDATE currencies SET is_favorite = :is_favorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, is_favorite: Boolean)

    @Query("SELECT * FROM currencies WHERE is_favorite = 1")
    suspend fun getFavoriteCurrencyList(): MutableList<CurrencyList>?

    @Query("SELECT * FROM currencies WHERE name='USD'")
    suspend fun getUSD(): CurrencyList

    @Query("SELECT * FROM currencies WHERE name='RUB'")
    suspend fun getRUB(): CurrencyList

    @Query("SELECT * FROM exchanges")
    suspend fun getExchangeHistory(): MutableList<CurrencyExchange>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExchange(exchange: CurrencyExchange)
}