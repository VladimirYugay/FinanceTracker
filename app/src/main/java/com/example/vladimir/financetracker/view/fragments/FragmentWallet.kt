package com.example.vladimir.financetracker.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.Constants
import com.example.vladimir.financetracker.R
import kotlinx.android.synthetic.main.fragment_wallet.*

class FragmentWallet: Fragment() {

    var currentWalletName: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents(arguments)
    }

    private fun initComponents(bundle: Bundle?){
        if(bundle != null){
            currentWalletName = bundle.getString(Constants.NAME)
            name_fragment_wallet.text = bundle.getString(Constants.NAME)
            balance_fragment_wallet.text = bundle.getString(Constants.BALANCE)
        }
    }

}