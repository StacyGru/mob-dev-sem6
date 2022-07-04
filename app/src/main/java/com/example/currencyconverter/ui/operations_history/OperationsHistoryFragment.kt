package com.example.currencyconverter.ui.operations_history

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.MainActivity
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.OperationsHistoryBinding
import com.example.currencyconverter.domain.model.CurrencyExchange
import com.example.currencyconverter.ui.filters.FiltersFragment

@Suppress("IMPLICIT_CAST_TO_ANY")
class OperationsHistoryFragment : Fragment() {

    private lateinit var binding: OperationsHistoryBinding
    private val viewModel: OperationsHistoryViewModel
        get() = (activity as MainActivity).viewModel
    private lateinit var adapter: OperationsHistoryAdapter
    private lateinit var recyclerView: RecyclerView
    var exchangeHistoryList: MutableList<CurrencyExchange> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OperationsHistoryAdapter(exchangeHistoryList)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = OperationsHistoryBinding.inflate(inflater, container, false)
        val fragment = FiltersFragment()
        binding.filters.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commitNow()
        }
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        getExchangeHistory("За всё время")
        viewModel.filter.observe(viewLifecycleOwner) {
            binding.filterState.text = it
            getExchangeHistory(it)
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getExchangeHistory(filter: String) {
        val historyList = when (filter) {
            "За всё время" -> viewModel.getExchangeHistory()
            "За сегодня" -> viewModel.getCurrentDayExchangeHistory()
            "За этот месяц" -> viewModel.getCurrentMonthExchangeHistory()
            else -> viewModel.getExchangeHistory()
        }

        historyList.let { newHistory ->
            exchangeHistoryList = emptyList<CurrencyExchange>().toMutableList()
            newHistory.forEach { item ->
                exchangeHistoryList.add(item)
            }
            adapter.setList(exchangeHistoryList)
        }
        }

}