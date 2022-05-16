package com.example.currencyconverter.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.domain.model.DataToUse
import com.example.currencyconverter.ui.currency_list.CurrencyActionListener
import kotlinx.android.synthetic.main.currency_list_item.view.*

class CurrencyAdapter:RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currencyList = emptyList<CurrencyList>()

    class CurrencyViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_list_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.itemView.currency_name.text = currencyList[position].name
        holder.itemView.currency_rate.text = currencyList[position].value.toString()
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CurrencyList>){
        currencyList = list
        notifyDataSetChanged()
    }

//    override fun onViewAttachedToWindow(holder: CurrencyViewHolder) {
//        super.onViewAttachedToWindow(holder)
//        holder.itemView.setOnClickListener {
//            actionListener.currencyExchange(currencyList[holder.absoluteAdapterPosition])
//        }
//    }

    override fun onViewDetachedFromWindow(holder: CurrencyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}


