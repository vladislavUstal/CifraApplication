package ru.vladislav.cifraapplication.repositories

import io.reactivex.Single
import retrofit2.Response
import ru.vladislav.cifraapplication.data.model.Bank

interface IBankRepository {

    fun getBankList(): Single<Response<List<Bank>>>

}