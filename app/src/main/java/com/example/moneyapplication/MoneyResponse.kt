package com.example.moneyapplication

import com.google.gson.annotations.SerializedName

data class MoneyResponse(
    @SerializedName("Valute")
    val items: Map<String, MoneyItem>
)

data class MoneyItem(

    @SerializedName("ID")
    var iD: String,

    @SerializedName("NumCode")
    var numCode: String,

    @SerializedName("CharCode")
    var charCode: String,

    @SerializedName("Nominal")
    var nominal: Int,

    @SerializedName("Name")
    var name: String,

    @SerializedName("Value")
    var value: Double,

    @SerializedName("Previous")
    var previous: Double,
)