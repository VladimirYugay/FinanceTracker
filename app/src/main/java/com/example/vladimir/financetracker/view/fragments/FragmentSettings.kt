package com.example.vladimir.financetracker.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import com.example.vladimir.financetracker.LocaleUtils
import com.example.vladimir.financetracker.R

class FragmentSettings : Fragment(){

    private lateinit var localeUtils: LocaleUtils

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_settings, container, false).also {
                localeUtils = LocaleUtils(context!!)
                it.findViewById<Toolbar>(R.id.activity_settings_toolbar).setNavigationOnClickListener {
                    fragmentManager?.popBackStack()
                }
                it.findViewById<Button>(R.id.settings_button_english).setOnClickListener {
                    localeUtils.setNewLocale("en")
                    fragmentManager?.popBackStack()
                }
                it.findViewById<Button>(R.id.settings_button_russian).setOnClickListener {
                    localeUtils.setNewLocale("ru")
                    fragmentManager?.popBackStack()
                }
            }
}