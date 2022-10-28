package ru.vladislav.cifraapplication.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vladislav.cifraapplication.BaseApplication
import ru.vladislav.cifraapplication.data.model.Bank
import ru.vladislav.cifraapplication.data.model.ErrorUtils.parseError
import ru.vladislav.cifraapplication.repositories.IBankRepository
import javax.inject.Inject


class MainViewModel : ViewModel() {

    @Inject
    lateinit var bankRepository: IBankRepository

    private val compositeDisposable = CompositeDisposable()

    val loading: ObservableField<Boolean> = ObservableField<Boolean>(true)

    val errorText: ObservableField<String> = ObservableField<String>()

    private val _bankList = MutableLiveData<List<Bank>>()
    val bankList: LiveData<List<Bank>> = _bankList

    init {
        BaseApplication.get().appComponent.inject(this)
    }

    fun initBankList() {
        compositeDisposable.add(
            bankRepository.getBankList()
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    loading.set(false)
                    if (response.isSuccessful) {
                        errorText.set("")
                        _bankList.postValue(response.body() ?: listOf())
                    } else {
                        val error = parseError(response)
                        errorText.set(error?.message())
                    }
                }, {
                    Log.e("error", it.localizedMessage ?: "")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}