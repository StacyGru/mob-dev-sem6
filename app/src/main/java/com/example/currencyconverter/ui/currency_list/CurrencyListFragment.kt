package com.example.currencyconverter.ui.currency_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.repository.RepositoryInitialization
import com.example.currencyconverter.databinding.CurrencyListBinding
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.recycler_view.CurrencyAdapter
import com.example.currencyconverter.ui.currency_exchange.CurrencyExchangeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.currency_list.view.*
import kotlinx.android.synthetic.main.currency_list_item.view.*

class CurrencyListFragment : Fragment() {

    private lateinit var binding: CurrencyListBinding
    lateinit var viewModel: CurrencyListViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = CurrencyListViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[CurrencyListViewModel::class.java]

        viewModel.init()
        adapter = CurrencyAdapter(object: CurrencyActionListener {
            override fun currencyExchange(currency: CurrencyList) {
                val fragment = CurrencyExchangeFragment()
                val bundle = Bundle()
                bundle.putSerializable("currency", currency)
                fragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, fragment)
                    .commitNow()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CurrencyListBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CurrencyListBinding.inflate(layoutInflater, container, false)


        binding.refresh.setOnClickListener {
            Log.d("MY_TAG_DB", "button clicked")
            viewModel.getRetrofitCurrencyList()
        }

        viewModel.liveData.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }
    }

}