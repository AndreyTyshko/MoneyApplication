package com.example.moneyapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class MoneyListFragment:Fragment(R.layout.fragment_money_list) {

    private lateinit var  moneyListViewModel: MoneyListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moneyListViewModel  = ViewModelProviders.of(this).get(MoneyListViewModel::class.java)
        moneyListViewModel.fetchMoneyList((activity?.application as? MoneyApp)?.moneyApi)

        initUi()

        observeViewModel()
    }

    //val modelList: List<MoneyItem>=
    private fun initUi() {
        //val CustomDropDownAdapter = CustomDropDownAdapter(this, moneyListViewModel)
    }

    private fun observeViewModel() {
        moneyListViewModel.state.observe(this) { list ->
            {
//TODO записываешь список в адаптер рекуклера
            }
        }
    }
}