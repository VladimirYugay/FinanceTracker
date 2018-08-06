package com.example.vladimir.financetracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.Constants
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.interfaces.IChangeFragmentCallback
import com.example.vladimir.financetracker.view.activities.ActivityMain
import com.example.vladimir.financetracker.view.adapters.AdapterWallets
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel
import kotlinx.android.synthetic.main.fragment_wallets.*

class FragmentWallets : Fragment() {

    private lateinit var mViewModel: FinanceTrackerViewModel
    private lateinit var mWalletsAdapter: AdapterWallets
    private lateinit var mChangeFragmentCallback: IChangeFragmentCallback

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mChangeFragmentCallback = context as ActivityMain
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(FinanceTrackerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListener()
    }

    private fun observeViewModel() {
        mViewModel.observableWallets.observe(viewLifecycleOwner, Observer {
            it?.forEach {
                val fragment = FragmentWallet()
                val bundle = Bundle()
                bundle.putString(Constants.NAME, it.name)
                bundle.putString(Constants.BALANCE, it.balance.toString())
                fragment.arguments = bundle
                mWalletsAdapter.addFragment(fragment)
                mWalletsAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initComponents() {
        mWalletsAdapter = AdapterWallets(childFragmentManager)
        view_pager_fragment_wallets.adapter = mWalletsAdapter
        fragment_wallets_toolbar.inflateMenu(R.menu.menu_fragment_wallets)
        observeViewModel()
    }

    private fun initComponentsListener() {

        fragment_wallets_toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        fragment_wallets_toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_add_wallet) {
                mChangeFragmentCallback.changeFragment(FragmentAddWallet())
                true
            } else {
                false
            }
        }

        view_pager_fragment_wallets.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(p0: Int) {
                val fragmentWallet = mWalletsAdapter.getItem(p0) as FragmentWallet
                fragmentWallet.currentWalletName?.let {
                    mViewModel.changeWallet(it)
                }
            }
        })
    }
}