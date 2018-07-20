package com.example.vladimir.financetracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.computations.MoneyOperations
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.action_about -> {
                    startActivity(Intent(this, ActivityAbout::class.java))
                }
                R.id.action_settings -> {
                    startActivity(Intent(this, ActivitySettings::class.java))
                }
            }
        }
        return true
    }

    private fun initComponents(){
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar.findViewById(R.id.activity_main_toolbar))
        activity_main_txt_balance_rub.text = MoneyOperations.getBalanceInRoubles().toString()
        activity_main_txt_balance_dollar.text = MoneyOperations.getBalanceInDollars().toString()
    }
}
