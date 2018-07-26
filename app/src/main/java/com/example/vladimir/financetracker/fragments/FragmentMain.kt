package com.example.vladimir.financetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.computations.entity.Operation
import com.example.vladimir.financetracker.Routes
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.activities.ActivityMain
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTextRoubles: TextView
    private lateinit var mTextDollars: TextView
    private var BALANCE = 1000.0
    private var operations: ArrayList<Operation> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents(){
        bottom_appbar.replaceMenu(R.menu.menu_main)
    }

    private fun initComponentsListeners(){
        bottom_appbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_wallets -> {
                    goTo(Routes.WALLET_FRAGMENT)
                    true
                }
                R.id.action_settings -> {
                    goTo(Routes.SETTINGS_FRAGMENT)
                true
                }
                R.id.action_about -> {
                    goTo(Routes.ABOUT_FRAGMENT)
                    true
                }
                else -> false
            }
        }
    }


    private fun goTo(fragmentName: String ){
        val intent = Intent(context, ActivityMain::class.java)
        intent.putExtra(Routes.DESTINATION_FRAGMENT, fragmentName)
        startActivity(intent)
    }
}