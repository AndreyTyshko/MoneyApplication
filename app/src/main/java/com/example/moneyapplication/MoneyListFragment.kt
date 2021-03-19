package com.example.moneyapplication

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders


class MoneyListFragment : Fragment(R.layout.fragment_money_list) {
    var spinnerFrom: Spinner? = null
    var spinnerTo: Spinner? = null
    var fromTextView: TextView? = null
    var toTextView: TextView? = null
    var editText: EditText? = null
    var tvResult: TextView? = null
    var nominalFrom: Int = 0
    var nominalTo: Int = 0

    var tv: TextView? = null
    var cnt = spinnerFrom

    var pos: Int = 0
    var pos2: Int = 0

    private val COUNT_KEY = "COUNT_KEY"


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY, pos)
        outState.putInt(COUNT_KEY, pos2)
    }


    private var adapter: CurrencySpinnerAdapter? = null
    private var adapter2: CurrencySpinnerAdapter? = null
    private lateinit var moneyListViewModel: MoneyListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moneyListViewModel = ViewModelProviders.of(this).get(MoneyListViewModel::class.java)

        moneyListViewModel.fetchMoneyList((activity?.application as? MoneyApp)?.moneyApi)
        //val leftSpinner = view.findViewById<Spinner>(R.id.spinnerLeft)

        initUi()

        observeViewModel()

        pos = savedInstanceState?.getInt("COUNT_KEY") ?: 0
        pos2 = savedInstanceState?.getInt("COUNT_KEY") ?: 0
    }


    private fun initUi() {
        spinnerFrom = view?.findViewById(R.id.spinnerFrom)
        spinnerTo = view?.findViewById(R.id.spinnerTo)
        fromTextView = view?.findViewById(R.id.tvFrom)
        toTextView = view?.findViewById(R.id.tvTo)
        editText = view?.findViewById(R.id.etTo)
        tvResult = view?.findViewById(R.id.tvResult)

        adapter = context?.let { CurrencySpinnerAdapter(it, R.layout.custom_spinner_item) }
        adapter2 = context?.let { CurrencySpinnerAdapter(it, R.layout.custom_spinner_item) }

        spinnerFrom?.adapter = adapter
        spinnerTo?.adapter = adapter2

        spinnerFrom?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                nominalFrom = (spinnerFrom?.selectedItem as MoneyItem).nominal
                val q = ((spinnerFrom?.selectedItem as MoneyItem).value / nominalFrom).toFloat()
                pos = spinnerFrom?.selectedItemPosition ?: 0



                fromTextView?.text = q.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinnerTo?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    nominalTo = (spinnerFrom?.selectedItem as MoneyItem).nominal
                    val q2 = ((spinnerTo?.selectedItem as MoneyItem).value / nominalTo).toFloat()
                    pos2 = spinnerFrom?.selectedItemPosition ?: 0
                    //val nominalToEnd=q2/nominalTo
                    toTextView?.text = q2.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }


            }

        editText?.addTextChangedListener(textWatcher)
    }


    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            calcuiate(s)
        }


    }

    private fun calcuiate(s: CharSequence?) {
        try {
            val from = toTextView?.text.toString().toDouble()
            val to = fromTextView?.text.toString().toDouble()
            val value = s.toString().toDouble()
            val result = (from / to * value).toFloat()
            tvResult?.setText(result.toString())
        } catch (e: NumberFormatException) {
            e.stackTraceToString()
        }
    }

    private fun observeViewModel() {
        moneyListViewModel.state.observe(this) { list ->
            adapter?.list = list.toList()
            adapter2?.list = list.toList()
            spinnerFrom?.setSelection(pos)

        }
    }

    /* override fun onSaveInstanceState(outState: Bundle) {
         super.onSaveInstanceState(outState)
         outState.putBundle(COUNT_KEY, outState)
         Log.d(COUNT_KEY, "onSaveInstanceState")
     }

     fun onRestoreInstanceState(savedInstanceState: Bundle) {
         onRestoreInstanceState(savedInstanceState)
         cnt = savedInstanceState.getInt("COUNT_KEY")
         //Log.d(LOG_TAG, "onRestoreInstanceState")
     }*/


}


