package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.getStringArray
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_add_wallet.*

class FragmentAddWallet : Fragment(){

    val currency = getStringArray(R.array.currencies)
    val currencyValues = arrayOf("RUB", "USD")
    var selectedCurrency : String? = currencyValues[0]

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

                if(mViewModel.addWallet(
                        Wallet(
                                fragment_add_wallet_name.text.toString(),
                                fragment_add_wallet_value.text.toString().toDouble(),
                                selectedCurrency!!
                        )
                )){
                    fragmentManager?.popBackStackImmediate()
                }else{
                    Toast.makeText(context, R.string.existing_wallet, Toast.LENGTH_SHORT).show()
                }

            }
        }

        fragment_add_wallet_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        fragment_add_wallet_currency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCurrency = currencyValues[0]
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCurrency = currencyValues[position]
            }
        }
    }
}