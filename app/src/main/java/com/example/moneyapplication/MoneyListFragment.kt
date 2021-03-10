package com.example.moneyapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class MoneyListFragment:Fragment(R.layout.fragment_money_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moneyListViewModel  = ViewModelProviders.of(this).get(MoneyListViewModel::class.java)
        moneyListViewModel.fetchMoneyList((activity?.application as? MoneyApp)?.moneyApi)
    }
}