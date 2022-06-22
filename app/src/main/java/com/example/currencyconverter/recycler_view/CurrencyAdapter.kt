package com.example.currencyconverter.recycler_view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyListItemBinding
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.domain.model.DataToUse
import com.example.currencyconverter.ui.currency_list.CurrencyActionListener
import kotlinx.android.synthetic.main.currency_list_item.view.*
import java.text.DecimalFormat

class CurrencyAdapter(
    private val actionListener: CurrencyActionListener
) :RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currencyList = emptyList<CurrencyList>()
//    lateinit var actionListener: CurrencyActionListener

//    class CurrencyViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyListItemBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CurrencyList>){
        currencyList = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CurrencyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            actionListener.currencyExchange(currencyList[holder.absoluteAdapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: CurrencyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

    inner class CurrencyViewHolder(private val binding: CurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrencyList) = binding.run {
            currencyName.text = item.name
            currencyRate.text = item.value.toString()
            if (item.is_favorite) {
                toFavorite.setImageResource(R.drawable.icon_star)
            } else {
                toFavorite.setImageResource(R.drawable.icon_star_outline)
            }

            root.setOnClickListener {
                actionListener.currencyExchange(item)
            }

            toFavorite.setOnClickListener {
                actionListener.onCurrencyFavorite(item.copy(is_favorite = !item.is_favorite))
            }

            root.setOnLongClickListener {
                actionListener.currencyUp(item)
                root.setOnClickListener {
                    actionListener.currencyUp(item)
                }
                true
            }
        }

    }
}


