package com.example.currencyconverter.ui.currency_exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.R
import com.example.currencyconverter.data.room.repository.RepositoryInitialization
import com.example.currencyconverter.databinding.CurrencyExchangeBinding
import com.example.currencyconverter.domain.model.CurrencyList
import com.example.currencyconverter.ui.currency_list.CurrencyListFragment
import java.text.DecimalFormat

class CurrencyExchangeFragment : Fragment() {

    private lateinit var binding: CurrencyExchangeBinding
    lateinit var currency: CurrencyList
    private lateinit var viewModel: CurrencyExchangeViewModel
    lateinit var firstCurrency: CurrencyList
    lateinit var secondCurrency: CurrencyList
    private var secondNameIs = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = CurrencyExchangeViewModelFactory(RepositoryInitialization.getRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory)[CurrencyExchangeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrencyExchangeBinding.inflate(layoutInflater, container, false)
        currency = arguments?.getSerializable("currency") as CurrencyList
        firstCurrency = currency
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            var fragment = CurrencyListFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commitNow()
        }

        if (arguments?.getSerializable("currencyUp") != null) {   // после выбора двух валют
            var currentCurrencyUp = arguments?.getSerializable("currencyUp") as CurrencyList
            firstCurrency = currentCurrencyUp
            secondCurrency = currency

        }
        else {  // после одинарного клика
            if (!getFavoriteCurrencyList()) {   // если среди favorite currencies не нашлось отличающихся
                secondCurrency = if (firstCurrency.name == "RUB") {
                    viewModel.getUSD()
                } else {
                    viewModel.getRUB()
                }
            }
        }

        binding.firstCurrencyName.text = firstCurrency.name
        binding.firstCurrencyAmount.setText("1")
        binding.secondCurrencyName.text = secondCurrency.name
        binding.secondCurrencyAmount.setText(
            DecimalFormat("#0.0000").format(
                firstCurrency.value / secondCurrency.value
            )
        )

        binding.firstCurrencyAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    if (start >= 0) {
                        val firstCurrencyAmount = s.toString().toDouble()
                        val secondCurrencyAmount =
                            (firstCurrency.value * firstCurrencyAmount) / secondCurrency.value
                        binding.secondCurrencyAmount.setText(
                            DecimalFormat("#0.0000").format(
                                secondCurrencyAmount
                            )
                        )
                    }
                }
                else
                    binding.secondCurrencyAmount.text.clear()
            }
        })
    }

    private fun getFavoriteCurrencyList(): Boolean{
        if(viewModel.getFavoriteCurrencyList()?.size!! >= 1){
            viewModel.getFavoriteCurrencyList().let { newCurrency ->
                newCurrency?.forEach { currency ->
                    if (!secondNameIs) {
                        if (currency.name != firstCurrency.name) {
                            secondCurrency = currency
                            secondNameIs = true
                        }
                    }
                }
            }
        }
        return secondNameIs
    }
}