package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.Routes
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.view.activities.ActivityMain
import com.example.vladimir.financetracker.view.adapters.AdapterTransactions
import com.example.vladimir.financetracker.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment() {

    lateinit var mViewModel: WalletViewModel
    lateinit var mAdapter: AdapterTransactions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
        mAdapter = AdapterTransactions()
        recycler_fragment_main.adapter = mAdapter
        recycler_fragment_main.layoutManager = LinearLayoutManager(context)
        observeViewModel(mViewModel)

        initComponents()
        initComponentsListeners()
    }


    private fun observeViewModel(viewModel: WalletViewModel) {
        // Update the list when the data changes
        viewModel.getObservableField().observe(this, Observer<Wallet> { wallet ->
            if (wallet != null) {
                wallet.transactions?.let {
                    mAdapter.setTransactionsList(it) }
            }
        })
    }

    private fun initComponents(){
        bottom_appbar.replaceMenu(R.menu.menu_main)
    }

    private fun initComponentsListeners(){
        fab.setOnClickListener {
            goTo(Routes.TRANSACTION_FRAGMENT)
        }
        bottom_appbar.setNavigationOnClickListener {
            goTo(Routes.WALLET_FRAGMENT)
        }
        bottom_appbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_statistics -> {
                    goTo(Routes.STATISTICS_FRAGMENT)
                    true
                }
                R.id.action_settings -> {
                    goTo(Routes.SETTINGS_FRAGMENT)
                true
                }
                R.id.action_about -> {
                    goTo(Routes.ABOUT_FRAGMENT)
                    true
                }
                else -> false
            }
        }
    }


    private fun goTo(fragmentName: String ){
        val intent = Intent(context, ActivityMain::class.java)
        intent.putExtra(Routes.DESTINATION_FRAGMENT, fragmentName)
        startActivity(intent)
    }
}