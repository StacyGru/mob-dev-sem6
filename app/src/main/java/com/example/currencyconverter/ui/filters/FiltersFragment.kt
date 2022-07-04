package com.example.currencyconverter.ui.filters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.currencyconverter.MainActivity
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyExchangeBinding
import com.example.currencyconverter.databinding.FiltersFragmentBinding
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.ui.currency_list.CurrencyListFragment
import com.example.currencyconverter.ui.operations_history.OperationsHistoryFragment
import com.example.currencyconverter.ui.operations_history.OperationsHistoryViewModel

class FiltersFragment : Fragment() {

    private val viewModel: OperationsHistoryViewModel
        get() = (activity as MainActivity).viewModel
    private lateinit var binding: FiltersFragmentBinding
    var fragment = OperationsHistoryFragment()
    var bundle = Bundle()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FiltersFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allTime.setOnClickListener {
            viewModel.filter.postValue("За всё время")
//            viewModel.getExchangeHistory()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commitNow()
        }

        binding.lastWeek.setOnClickListener {
            viewModel.filter.postValue("За неделю")
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commitNow()
        }
        binding.lastMonth.setOnClickListener {

            fragment.arguments = bundle
            viewModel.filter.postValue("За месяц")
//            viewModel.getLastMonthExchangeHistory()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commitNow()
        }
    }

}