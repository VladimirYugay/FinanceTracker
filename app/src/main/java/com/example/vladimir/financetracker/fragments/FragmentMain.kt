package com.example.vladimir.financetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.computations.MoneyOperations
import com.example.computations.entity.Operation
import com.example.vladimir.financetracker.FlowConstants
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.Utils
import com.example.vladimir.financetracker.activities.ActivityAbout
import com.example.vladimir.financetracker.activities.ActivityMain
import com.example.vladimir.financetracker.activities.ActivitySettings
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment(){

    private lateinit var mFragmentToolbar: Toolbar
    private lateinit var mTextRoubles: TextView
    private lateinit var mTextDollars: TextView
    private var BALANCE = 1000.0
    private var operations: ArrayList<Operation> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentToolbar = view.findViewById(R.id.fragment_main_toolbar)
        mTextRoubles = view.findViewById(R.id.fragment_main_txt_balance_rub_value)
        mTextDollars = view.findViewById(R.id.fragment_main_txt_balance_usd_value)
        mTextRoubles.text = resources.getString(R.string.balance_message, Utils.formatNumber(MoneyOperations.convertCurrency(MoneyOperations.getBalance(BALANCE, operations),0.016)), resources.getString(R.string.rub))
        mTextDollars.text = resources.getString(R.string.balance_message, Utils.formatNumber(MoneyOperations.getBalance(BALANCE, operations)), resources.getString(R.string.usd))

        initComponentsListeners()
        initToolbar()


    }

    private fun initComponentsListeners(){
        fragment_main_add_expenditure_button.setOnClickListener {
            val intent = Intent(context, ActivityMain::class.java)
            intent.putExtra(FlowConstants.LOAD_FRAGMENT, FlowConstants.LOAD_EXPENDITURE_FRAGMENT)
            startActivity(intent)
        }
    }

    private fun initToolbar(){
        mFragmentToolbar.inflateMenu(R.menu.menu_main)
        mFragmentToolbar.title = resources.getString(R.string.app_name)
        mFragmentToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_settings -> {
                    startActivity(Intent(context, ActivitySettings::class.java))
                    true
                }
                R.id.action_about -> {
                    startActivity(Intent(context, ActivityAbout::class.java))
                    true
                }
                else -> false
            }
        }
    }
}