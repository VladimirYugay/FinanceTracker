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
import com.example.vladimir.financetracker.alertConfirm
import com.example.vladimir.financetracker.getStringArray
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_edit_transaction.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentEditTransaction : Fragment() {

    companion object {

        private const val EXTRA_TRANSACTION_ID = "com.example.vladimir.financetracker.view.fragments.FragmentEditTransaction.transactionId"

        fun newInstance(transactioId: Long): FragmentEditTransaction {
            val fragment = FragmentEditTransaction()
            val bundle = Bundle()
            bundle.putLong(EXTRA_TRANSACTION_ID, transactioId)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var mViewModel: FinanceTrackerViewModel

    val currency = getStringArray(R.array.currencies)
    val currencyValues = arrayOf("RUB", "USD")

    var selectedCurrency = currencyValues[0]
    var selectedDate = Date()

    var categories: Array<String>? = null
    var selectedCategory = ""

    val categoryExpenditure = getStringArray(R.array.categories_in)
    val categoryProfit = getStringArray(R.array.categories_out)

    private lateinit var editedTransaction: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_edit_transaction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        fragment_transaction_category.setAdapter(object : ArrayAdapter<String>(context,
                R.layout.spinner_item, categoryExpenditure) {
        })

        fragment_transaction_currency.setAdapter(object : ArrayAdapter<String>(context,
                R.layout.spinner_item, currency) {
        })

        editedTransaction = mViewModel.getEditedTransaction()!!
        editedTransaction?.let { transaction ->
            if (transaction.expenditure) {
                radio_group_operation_type.check(R.id.radio_operation_type_out)
                categories = categoryExpenditure
            }
            else {
                radio_group_operation_type.check(R.id.radio_operation_type_in)
                categories = categoryProfit
            }
            var indexOfCategory = categories?.indexOfFirst { transaction.category == it }
            if (indexOfCategory == null || indexOfCategory < 0) {
                indexOfCategory = 0;
            }
            fragment_transaction_category.selectedIndex = indexOfCategory
            var indexOfCurrency = currencyValues.indexOfFirst { transaction.currency == it}
            if (indexOfCurrency == null || indexOfCurrency < 0) {
                indexOfCurrency = 0;
            }
            fragment_transaction_currency.selectedIndex = indexOfCurrency
            fragment_transaction_name.setText(transaction.name)
            fragment_transaction_value.setText(String.format("%.2f", transaction.value).replace(",", "."))
            fragment_transaction_date.setText(transaction.date)
        }
    }

    private fun initComponentsListeners() {
        radio_group_operation_type.setOnCheckedChangeListener { radioGroup, i ->
            if(i == R.id.radio_operation_type_out) {
                categories = categoryExpenditure
                fragment_transaction_category.setAdapter(object : ArrayAdapter<String>(context,
                        R.layout.spinner_item, categoryExpenditure) {
                })
            }
            else {
                categories = categoryProfit
                fragment_transaction_category.setAdapter(object : ArrayAdapter<String>(context,
                        R.layout.spinner_item, categoryProfit) {
                })
            }
        }

        button_delete_transaction.setOnClickListener {
            alertConfirm(context!!, R.string.text_delete_confirm) {
                mViewModel.deleteEditedTransaction()
                fragmentManager?.popBackStackImmediate()
            }
        }

        fragment_wallets_ok.setOnClickListener {
            if (fragment_transaction_name.text.toString().isNotBlank()
                    && fragment_transaction_value.text.toString().isNotBlank()
                    && fragment_transaction_date.text.toString().isNotBlank()) {

                var value = fragment_transaction_value.text.toString().toDouble()
                if(radio_operation_type_out.isChecked) value = -value
                if(fragment_transaction_currency.equals(currency.first())) value /= mViewModel.getUSD()
//                mViewModel.updateTransaction(editedTransaction)
                mViewModel.updateTransaction(editedTransaction.copy(
                        name = fragment_transaction_name.text.toString(),
                        expenditure = radio_operation_type_out.isChecked,
                        currency = selectedCurrency,
                        category = if (selectedCategory.isNotBlank()) selectedCategory else categories!![0],
                        value = value,
                        date = fragment_transaction_date.text.toString()
                        ))
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
                selectedDate = date
                val formatted = SimpleDateFormat.getDateInstance().format(date)
                fragment_transaction_date.setText(formatted)
            }

            DatePickerDialog(activity, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        fragment_transaction_currency.setOnItemSelectedListener { view, position, id, item ->
            selectedCurrency = currencyValues[position]
        }

        fragment_transaction_category.setOnItemSelectedListener { view, position, id, item ->
            selectedCategory = item.toString()
        }
    }
}