package com.example.vladimir.financetracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.vladimir.financetracker.R

class ActivitySettings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initToolbar()
    }

    private fun initToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.activity_settings_toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.title = resources.getString(R.string.settings)
        toolbar.setNavigationOnClickListener {
            finish()
        }

    }
}
