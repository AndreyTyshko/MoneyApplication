package com.example.moneyapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CurrencySpinnerAdapter(
    context: Context,
    resource: Int
) : ArrayAdapter<MoneyItem>(context, resource) {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var list: List<MoneyItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): MoneyItem = list[position]

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        inflater.inflate(R.layout.custom_spinner_item, parent, false).apply {
            findViewById<TextView>(R.id.textNameMoney).text = list[position].charCode
            findViewById<TextView>(R.id.textNominalMoney).text = list[position].value.toString()
        }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        inflater.inflate(R.layout.custom_spinner_item, parent, false).apply {
            findViewById<TextView>(R.id.textNameMoney).text = list[position].charCode
            findViewById<TextView>(R.id.textNominalMoney).text = list[position].value.toString()
        }
}