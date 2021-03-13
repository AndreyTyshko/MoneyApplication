package com.example.moneyapplication

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


class MoneyListFragment : Fragment(R.layout.fragment_money_list) {
    var leftSpinner: Spinner? = null
    private var adapter: CurrencySpinnerAdapter? = null
    private lateinit var moneyListViewModel: MoneyListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moneyListViewModel = ViewModelProviders.of(this).get(MoneyListViewModel::class.java)
        moneyListViewModel.fetchMoneyList((activity?.application as? MoneyApp)?.moneyApi)
        //val leftSpinner = view.findViewById<Spinner>(R.id.spinnerLeft)

        initUi()

        observeViewModel()
    }

    private fun initUi() {
        leftSpinner = view?.findViewById(R.id.spinnerLeft)
        adapter = context?.let { CurrencySpinnerAdapter(it, R.layout.custom_spinner_item) }

        leftSpinner?.adapter = adapter
    }

    private fun observeViewModel() {
        moneyListViewModel.state.observe(this) { list ->
            adapter?.list = list.toList()


            //val dropDownAdapter: ArrayAdapter<>


//TODO записываешь список в адаптер рекуклера
/**/
            /*val dropDownAdapter = DropDownAdapter(this, android.R.layout.simple_spinner_item, )
            leftSpinner?.adapter = DropDownAdapter*/

            /*   val DropDownAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
                   this,
                   android.R.layout.simple_spinner_item, arrayOf<State>( )
               )
*/
            /* val dropDownAdapter = ArrayAdapter<MoneyItem>(
                 this, R.layout.custom_spinner_item, moneyListViewModel.state
             )
             dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
             leftSpinner?.setAdapter(dropDownAdapter)*/

            //leftSpinner?.adapter=ArrayAdapter(activity, R.layout.custom_spinner_item, list) as SpinnerAdapter
            /*l list = arrayListOf<string>()
                 val adapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item, list) }*/


        }
    }
}