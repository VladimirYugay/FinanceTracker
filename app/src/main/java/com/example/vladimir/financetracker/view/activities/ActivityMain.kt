package com.example.vladimir.financetracker.view.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.example.vladimir.financetracker.*
import com.example.vladimir.financetracker.interfaces.IChangeFragmentCallback
import com.example.vladimir.financetracker.view.fragments.FragmentMain

class ActivityMain : AppCompatActivity(), IChangeFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            loadFragmentWithoutBackStack(FragmentMain())
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val fragment = Router().navigate(intent)
        if (fragment != null) {
            loadFragment(fragment)
        }
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_open, R.anim.fragment_close, R.anim.fragment_pop_open, R.anim.fragment_pop_close)
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }

    private fun loadFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_open, R.anim.fragment_close, R.anim.fragment_pop_open, R.anim.fragment_pop_close)
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }

}
