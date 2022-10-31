package ru.vladislav.cifraapplication.presentation.viewModel.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vladislav.cifraapplication.BaseApplication
import ru.vladislav.cifraapplication.data.api.ErrorUtils.parseError
import ru.vladislav.cifraapplication.data.model.Bank
import ru.vladislav.cifraapplication.domain.useCase.GetBankListUseCase
import javax.inject.Inject


class MainViewModel : ViewModel() {

    @Inject
    lateinit var getBankListUseCase: GetBankListUseCase

    val loading: ObservableField<Boolean> = ObservableField<Boolean>(true)

    val errorText: ObservableField<String> = ObservableField<String>()

    private val _bankList = MutableLiveData<List<Bank>>()
    val bankList: LiveData<List<Bank>> = _bankList

    init {
        BaseApplication.get().appComponent.inject(this)
    }

    fun initBankList() {
        getBankListUseCase.execute(
            onSuccess = { response ->
                loading.set(false)
                if (response.isSuccessful) {
                    errorText.set("")
                    _bankList.postValue(response.body() ?: listOf())
                } else {
                    val error = parseError(response)
                    errorText.set(error?.message())
                }
            },
            onError = {
                Log.e("pizden", it.localizedMessage ?: "")
            })
    }

    override fun onCleared() {
        super.onCleared()
        getBankListUseCase.dispose()
    }
}