package com.example.vladimir.financetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.computations.MoneyOperations
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.Utils
import com.example.vladimir.financetracker.activities.ActivityAbout
import com.example.vladimir.financetracker.activities.ActivitySettings
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment(){

    private lateinit var mFragmentToolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentToolbar = view.findViewById(R.id.fragment_main_toolbar)
        fragment_main_txt_balance_rub_value.text = resources.getString(R.string.balance_message, Utils.formatNumber(MoneyOperations.translateCurrency(0.016)), resources.getString(R.string.rub))
        fragment_main_txt_balance_usd_value.text = resources.getString(R.string.balance_message, Utils.formatNumber(MoneyOperations.getBalance()), resources.getString(R.string.usd))
        initToolbar()
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