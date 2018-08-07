package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.*
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

    override fun onResume() {
        super.onResume()
        hideSoftKeyboard()
    }

    private fun observeViewModel(viewModel: FinanceTrackerViewModel) {

        viewModel.observableTransactions.observe(viewLifecycleOwner, Observer {
            mAdapter.setTransactionsList(it)
        })

        viewModel.observableWallet.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                fragment_main_balance.text = "${it?.balance.fmtMoney()} ${if (it?.currency == "USD") getString(R.string.usd) else getString(R.string.rub)}"
                fragment_main_wallet.text = it?.name
            }
            else {
                fragment_main_balance.text = getString(R.string.text_no)
                fragment_main_wallet.text = getString(R.string.text_no)
            }
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
            if (mViewModel.observableWallet.value != null) {
                goTo(context, Routes.ADD_TRANSACTION_FRAGMENT)
            }
            else {
                alertError(activity!!, R.string.error_no_wallet)
            }
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