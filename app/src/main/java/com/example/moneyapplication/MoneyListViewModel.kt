package com.example.moneyapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MoneyListViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    val state = MutableLiveData<Collection<MoneyItem>>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchMoneyList(moneyApi: MoneyApi?) {
        if (state.value == null) {
            moneyApi?.let {
                compositeDisposable.add(
                    moneyApi.getMoneyList()
                        .map { it.items.values }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            ::logList,
                            ::logError
                        )
                )

            }
        }
    }

    private fun logList(list: Collection<MoneyItem>) {
        state.value = list
    }

    private fun logError(throwable: Throwable) {

    }

}

