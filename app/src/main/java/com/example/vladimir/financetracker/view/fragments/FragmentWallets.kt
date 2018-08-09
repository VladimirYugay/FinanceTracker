package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.interfaces.IChangeFragmentCallback
import com.example.vladimir.financetracker.view.activities.ActivityMain
import com.example.vladimir.financetracker.view.adapters.WalletsRecyclerViewAdapter
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_wallets.*

class FragmentWallets : Fragment() {

    private lateinit var mViewModel: FinanceTrackerViewModel
    private lateinit var mChangeFragmentCallback: IChangeFragmentCallback

    private lateinit var mWalletsAdapter: WalletsRecyclerViewAdapter
    private lateinit var mWalletsLayoutManager: LinearLayoutManager

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mChangeFragmentCallback = context as ActivityMain
    }

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
        mViewModel.observableWallets.observe(viewLifecycleOwner, Observer {
            mWalletsAdapter.setItems(it?.toList()!!)
        })
    }

    private fun initComponents() {
        mWalletsLayoutManager = LinearLayoutManager(context)
        mWalletsAdapter = WalletsRecyclerViewAdapter {
            mViewModel.changeWallet(it.name)
        }
        recycler_view_wallets.adapter = mWalletsAdapter
        recycler_view_wallets.layoutManager = mWalletsLayoutManager

        observeViewModel()
    }

    private fun initComponentsListener() {
        fragment_wallets_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        fragment_wallets_ok.setOnClickListener {
            mChangeFragmentCallback.changeFragment(FragmentAddWallet())
        }
    }
}