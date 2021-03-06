package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.currencyconverter.data.room.repository.RepositoryInitialization
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.ui.operations_history.OperationsHistoryViewModel
import com.example.currencyconverter.ui.operations_history.OperationsHistoryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: OperationsHistoryViewModel by viewModels {
        OperationsHistoryViewModelFactory(RepositoryInitialization.getRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.currency_list, R.id.operations_history, R.id.analytics
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        MAIN = this
    }
}