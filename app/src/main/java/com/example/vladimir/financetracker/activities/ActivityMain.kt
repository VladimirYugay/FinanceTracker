package com.example.vladimir.financetracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.fragments.FragmentMain
import com.example.vladimir.financetracker.interfaces.IChangeFragmentListener

class ActivityMain : AppCompatActivity(), IChangeFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            loadFragment(FragmentMain())
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            finish()
        }
        super.onBackPressed()
    }

    override fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).addToBackStack(fragment.javaClass.name).commit()
    }

}
