package com.example.currencyconverter.ui.currency_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyListBinding
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.recycler_view.CurrencyAdapter
import com.example.currencyconverter.ui.currency_exchange.CurrencyExchangeFragment
import kotlinx.android.synthetic.main.currency_list.view.*

class CurrencyListFragment : Fragment() {

    private var _binding: CurrencyListBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrencyListBinding.inflate(layoutInflater, container, false)

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[CurrencyListViewModel::class.java]
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        viewModel.getCurrencyList()
        viewModel.currencyList.observe(viewLifecycleOwner) { list ->
            adapter.setList(list.rates)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}