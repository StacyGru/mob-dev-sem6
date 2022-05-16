package com.example.currencyconverter.ui.currency_exchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyExchangeBinding
import com.example.currencyconverter.databinding.CurrencyListBinding
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.recycler_view.CurrencyAdapter
import com.example.currencyconverter.ui.currency_list.CurrencyListViewModel

class CurrencyExchangeFragment : Fragment() {

    private var _binding: CurrencyExchangeBinding? = null
    private val binding get() = _binding!!
    lateinit var currency: CurrencyList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CurrencyExchangeBinding.inflate(layoutInflater, container, false)
        currency = arguments?.getSerializable("currency") as CurrencyList
        return inflater.inflate(R.layout.currency_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[CurrencyExchangeViewModel::class.java]
        binding.currencyName.text = currency.name
        binding.currencyRate.hint = currency.value.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}