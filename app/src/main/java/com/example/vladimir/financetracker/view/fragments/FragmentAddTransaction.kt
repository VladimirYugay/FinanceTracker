package com.example.vladimir.financetracker.view.fragments

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_transaction.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentAddTransaction() : Fragment() {

    lateinit var mViewModel: FinanceTrackerViewModel
    val currency = arrayOf("Рубли", "Доллары")
    val categoryExpenditure = arrayOf("Еда", "Одежда", "Развлечения", "Другое")
    val categoryProfit = arrayOf("Зарплата", "Воровство", "Взяточничество", "Другое")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {

        fragment_transaction_category.adapter = object : ArrayAdapter<String>(context,
                R.layout.spinner_item, categoryExpenditure) {
        }

        fragment_transaction_currency.adapter = object : ArrayAdapter<String>(context,
                R.layout.spinner_item, currency) {
        }

    }


    private fun initComponentsListeners() {

        fragment_transaction_expendture.setOnClickListener {
            if(fragment_transaction_expendture.isChecked){
                fragment_transaction_category.adapter = object : ArrayAdapter<String>(context,
                        R.layout.spinner_item, categoryExpenditure) {
                }
            }else{
                fragment_transaction_category.adapter = object : ArrayAdapter<String>(context,
                        R.layout.spinner_item, categoryProfit) {
                }
            }
        }

        fragment_wallets_ok.setOnClickListener {
            if (fragment_transaction_name.text.toString().isNotBlank()
                    && fragment_transaction_value.text.toString().isNotBlank()
                    && fragment_transaction_date.text.toString().isNotBlank()) {

                var value = fragment_transaction_value.text.toString().toDouble()
                if(fragment_transaction_expendture.isChecked) value = -value
                if(fragment_transaction_currency.equals(currency.first())) value /= mViewModel.getUSD()
                mViewModel.addTransaction(Transaction(
                        fragment_transaction_name.text.toString(),
                        fragment_transaction_expendture.isChecked,
                        fragment_transaction_currency.selectedItem.toString(),
                        fragment_transaction_category.selectedItem.toString(),
                        value,
                        fragment_transaction_date.text.toString(),
                ""))
                fragmentManager?.popBackStackImmediate()
            }
        }

        fragment_transaction_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        fragment_transaction_date.setOnClickListener {
            val calendar = Calendar.getInstance()

            val dateListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val date = calendar.time
                val formatted = SimpleDateFormat("dd.MM.yyyy").format(date)
                fragment_transaction_date.setText(formatted)
            }

            DatePickerDialog(activity, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}