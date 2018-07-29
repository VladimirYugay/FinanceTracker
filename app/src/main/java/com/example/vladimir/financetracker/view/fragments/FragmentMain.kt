package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.Routes
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.goTo
import com.example.vladimir.financetracker.view.adapters.AdapterTransactions
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment() {

    lateinit var mViewModel: FinanceTrackerViewModel
    lateinit var mAdapter: AdapterTransactions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }


    private fun observeViewModel(viewModel: FinanceTrackerViewModel) {

        viewModel.getTransactions().observe(viewLifecycleOwner, Observer {
            mAdapter.setTransactionsList(it)
        })

        viewModel.observableWallet.observe(viewLifecycleOwner, Observer {
            fragment_main_balance.text = it?.balance.toString()
            fragment_main_wallet.text = it?.name
        })

    }

    private fun initComponents() {
        mAdapter = AdapterTransactions()
        recycler_fragment_main.adapter = mAdapter
        observeViewModel(mViewModel)
        bottom_appbar.replaceMenu(R.menu.menu_main)
    }

    private fun initComponentsListeners() {
        fab.setOnClickListener {
            goTo(context, Routes.ADD_TRANSACTION_FRAGMENT)
        }
        bottom_appbar.setNavigationOnClickListener {
            goTo(context, Routes.WALLETS_FRAGMENT)
        }
        bottom_appbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_statistics -> {
                    goTo(context, Routes.STATISTICS_FRAGMENT)
                    true
                }
                R.id.action_settings -> {
                    goTo(context, Routes.SETTINGS_FRAGMENT)
                    true
                }
                R.id.action_about -> {
                    goTo(context, Routes.ABOUT_FRAGMENT)
                    true
                }
                else -> false
            }
        }
    }

}