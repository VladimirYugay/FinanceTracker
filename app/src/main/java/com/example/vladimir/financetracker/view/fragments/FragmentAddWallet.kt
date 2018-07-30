package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.vladimir.financetracker.Constants
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.createId
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_add_wallet.*
import kotlinx.android.synthetic.main.fragment_transaction.*

class FragmentAddWallet : Fragment(){

    val currency = arrayOf("Рубли", "Доллары")
    lateinit var mViewModel: FinanceTrackerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents(){
        fragment_add_wallet_currency.adapter = object : ArrayAdapter<String>(context,
                R.layout.spinner_item, currency) {
        }
    }

    private fun initComponentsListeners(){
        fragment_add_wallet_add.setOnClickListener {
            if(fragment_add_wallet_name.text.toString().isNotEmpty()
                && fragment_add_wallet_value.toString().isNotEmpty()) {

                //CONVERT TO DOLLARS
                if(fragment_add_wallet_default.isChecked){
                    mViewModel.changeWallet(fragment_add_wallet_name.text.toString())
                }

                if(mViewModel.addWallet(Wallet(fragment_add_wallet_name.text.toString(),
                        fragment_add_wallet_value.text.toString().toDouble()))){
                    fragmentManager?.popBackStackImmediate()
                }else{
                    Toast.makeText(context, R.string.existing_wallet, Toast.LENGTH_SHORT).show()
                }

            }
        }

        fragment_add_wallet_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
    }
}