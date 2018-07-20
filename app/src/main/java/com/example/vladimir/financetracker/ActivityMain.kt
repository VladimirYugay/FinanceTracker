package com.example.vladimir.financetracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.computations.Functions
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_txt_balance_rub.text = Functions.getBalance().toString()
    }

}
