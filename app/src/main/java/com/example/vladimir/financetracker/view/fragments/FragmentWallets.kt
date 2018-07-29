package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.Routes
import com.example.vladimir.financetracker.goTo
import com.example.vladimir.financetracker.view.adapters.AdapterWallets
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_wallets.*

class FragmentWallets : Fragment() {

    private lateinit var mWalletsAdapter: AdapterWallets
    private lateinit var mViewModel: FinanceTrackerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListener()
    }

    private fun observeViewModel() {
        mViewModel.getWallets().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mWalletsAdapter.setWalletsList(it)
            }
        })
    }

    private fun initComponents() {
        mWalletsAdapter = AdapterWallets()
        fragment_wallets_recycler.adapter = mWalletsAdapter
        observeViewModel()
        fragment_wallets_toolbar.inflateMenu(R.menu.menu_fragment_wallets)
    }

    private fun initComponentsListener() {
        fragment_wallets_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        fragment_wallets_toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_add_wallet) {
                goTo(context, Routes.ADD_WALLET_FRAGMENT)
                true
            } else {
                false
            }
        }
    }
}