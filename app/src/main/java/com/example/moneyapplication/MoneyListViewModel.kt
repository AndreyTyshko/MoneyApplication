package com.example.moneyapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import com.example.moneyapplication.MoneyApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoneyListViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchMoneyList(moneyApi: MoneyApi?) {
        moneyApi?.let {
            compositeDisposable.add(moneyApi.getMoneyList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                       Log.d("TAG", it.Valute.map{it.value}.toString())
                    }, {

                    })
            )

        }


    }
}