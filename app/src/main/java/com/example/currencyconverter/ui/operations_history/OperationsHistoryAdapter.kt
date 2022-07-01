package com.example.currencyconverter.ui.operations_history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.databinding.CurrencyListItemBinding
import com.example.currencyconverter.databinding.OperationsHistoryItemBinding
import com.example.currencyconverter.domain.model.CurrencyExchange
import java.text.DecimalFormat

class OperationsHistoryAdapter(
    var exchangeHistoryList: MutableList<CurrencyExchange>
) : RecyclerView.Adapter<OperationsHistoryAdapter.OperationsHistoryViewHolder>() {

    inner class OperationsHistoryViewHolder(val binding: OperationsHistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationsHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OperationsHistoryItemBinding.inflate(inflater, parent, false)
        return OperationsHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OperationsHistoryViewHolder, position: Int) {
        var exchangeHistoryItem: CurrencyExchange = exchangeHistoryList[position]
        with(holder.binding){
            date.text = exchangeHistoryItem.date
            firstCurrencyAmount.text = DecimalFormat("#0.0000").format(exchangeHistoryItem.first_currency_amount)
            firstCurrencyName.text = exchangeHistoryItem.first_currency_name
            secondCurrencyAmount.text = DecimalFormat("#0.0000").format(exchangeHistoryItem.second_currency_amount)
            secondCurrencyName.text = exchangeHistoryItem.second_currency_name
        }
    }

    override fun getItemCount(): Int {
        return exchangeHistoryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<CurrencyExchange>){
        exchangeHistoryList = list
        notifyDataSetChanged()
    }
}