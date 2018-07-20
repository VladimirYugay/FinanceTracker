package com.example.vladimir.financetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.computations.MoneyOperations
import com.example.vladimir.financetracker.R
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
        fragment_main_txt_balance_rub.text = MoneyOperations.getBalanceInRoubles().toString()
        fragment_main_txt_balance_dollar.text = MoneyOperations.getBalanceInDollars().toString()

        initToolbar()
    }

    override fun onResume() {
        super.onResume()
        initComponentsListeners()
    }

    private fun initToolbar(){
        mFragmentToolbar.inflateMenu(R.menu.menu_main)
    }

    private fun initComponentsListeners(){
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