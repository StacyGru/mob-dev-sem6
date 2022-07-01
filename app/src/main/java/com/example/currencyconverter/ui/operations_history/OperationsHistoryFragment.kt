package com.example.currencyconverter.ui.operations_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.data.room.repository.RepositoryInitialization
import com.example.currencyconverter.databinding.OperationsHistoryBinding
import com.example.currencyconverter.domain.model.CurrencyExchange

class OperationsHistoryFragment : Fragment() {

    private lateinit var binding: OperationsHistoryBinding
    private lateinit var viewModel: OperationsHistoryViewModel
    private lateinit var adapter: OperationsHistoryAdapter
    private lateinit var recyclerView: RecyclerView
    var exchangeHistoryList: MutableList<CurrencyExchange> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = OperationsHistoryViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[OperationsHistoryViewModel::class.java]
        adapter = OperationsHistoryAdapter(exchangeHistoryList)
        getExchangeHistory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OperationsHistoryBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return binding.root
    }

    private fun getExchangeHistory() {
        viewModel.getExchangeHistory().let { newExchangeHistory ->
            exchangeHistoryList.clear()
            newExchangeHistory.forEach { item ->
                exchangeHistoryList.add(item)
            }
            adapter.setList(exchangeHistoryList)
        }
    }
}