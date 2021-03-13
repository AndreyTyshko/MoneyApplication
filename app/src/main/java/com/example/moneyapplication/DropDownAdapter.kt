package com.example.moneyapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class DropDownAdapter(val context: Context, var dataSource: List<ModelAdapter>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.nameMoney.text = dataSource.get(position).name

        val id = context.resources.getIdentifier(dataSource.get(position).name, "drawable", context.packageName)
        vh.nominalMoney.setBackgroundResource(id)

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val nameMoney: TextView
        val nominalMoney: TextView

        init {
            nameMoney = row?.findViewById(R.id.textNameMoney) as TextView
            nominalMoney = row?.findViewById(R.id.textNominalMoney) as TextView
        }
    }

}

