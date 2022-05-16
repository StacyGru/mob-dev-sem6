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
import com.example.currencyconverter.recycler_view.CurrencyAdapter
import kotlinx.android.synthetic.main.currency_list.view.*

class CurrencyListFragment : Fragment() {

    private var _binding: CurrencyListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(CurrencyListViewModel::class.java)

        _binding = CurrencyListBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val viewModel = ViewModelProvider(this).get(CurrencyListViewModel::class.java)
        val view = inflater.inflate(R.layout.currency_list, container, false)
        recyclerView = view.recyclerView
        adapter = CurrencyAdapter()
        recyclerView.adapter = adapter
        viewModel.getCurrencyList()
        viewModel.currencyList.observe(viewLifecycleOwner) { list ->
            adapter.setList(list.rates)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}