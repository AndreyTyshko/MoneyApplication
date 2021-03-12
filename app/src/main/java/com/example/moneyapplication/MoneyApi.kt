package com.example.moneyapplication

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface MoneyApi {

    @GET("./daily_json.js")
    //@Headers("Content-Type: application/json")
    fun getMoneyList(): Single<MoneyResponse>

}