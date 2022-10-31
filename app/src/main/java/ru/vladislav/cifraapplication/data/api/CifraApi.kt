package ru.vladislav.cifraapplication.data.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import ru.vladislav.cifraapplication.data.model.Bank

interface CifraApi {

    @POST("bank/available")
    fun getBankList(
        @Header("Content-type") type: String = "application/json",
        @Header("Authorization") auth: String = "Bearer 1"
    ): Single<Response<List<Bank>>>

}