package com.example.vladimir.financetracker.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.FlowConstants
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.fragments.FragmentExpenditure
import com.example.vladimir.financetracker.fragments.FragmentMain

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            loadFragmentWithoutBackStack(FragmentMain())
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        when(intent?.getStringExtra(FlowConstants.LOAD_FRAGMENT)){
            FlowConstants.LOAD_EXPENDITURE_FRAGMENT -> loadFragment(FragmentExpenditure())
            else -> {

            }
        }
    }


    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(fragment.javaClass.name).commit()
    }

    private fun loadFragmentWithoutBackStack(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

}
