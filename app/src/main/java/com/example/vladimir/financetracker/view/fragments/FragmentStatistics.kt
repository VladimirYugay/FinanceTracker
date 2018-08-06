package com.example.vladimir.financetracker.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vladimir.financetracker.R

class FragmentStatistics : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_statistics, container, false).also {
                it.findViewById<Toolbar>(R.id.activity_statistics_toolbar).setNavigationOnClickListener {
                    fragmentManager?.popBackStack()
                }
            }
}