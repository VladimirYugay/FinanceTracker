package com.example.vladimir.financetracker.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.vladimir.financetracker.R

class FragmentExpenditure() : Fragment() {

    private lateinit var mFragmentExpenditureToobar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_expenditure, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentExpenditureToobar = view.findViewById(R.id.fragment_expenditure_toolbar)
        initToolbar()
    }


    private fun initToolbar(){
        mFragmentExpenditureToobar.setNavigationIcon(R.drawable.ic_arrow_back)
        mFragmentExpenditureToobar.title = resources.getString(R.string.add_expenditure)
        mFragmentExpenditureToobar.inflateMenu(R.menu.menu_dropdown)
        mFragmentExpenditureToobar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        val spinnerItems = listOf(" ", "USD", "RUB")
        val menuItem = mFragmentExpenditureToobar.menu.findItem(R.id.toolbar_spinner)
        val spinner = menuItem.actionView as Spinner
        val arrayAdapter = object : ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems){
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
                lateinit var view: View
                if(position == 0){
                    val textView = TextView(context)
                    textView.height = 0
                    textView.visibility = View.GONE
                    view = textView
                }else{
                    view = super.getDropDownView(position, null, parent)
                }
                return view
            }
        }
        spinner.adapter = arrayAdapter

    }
}