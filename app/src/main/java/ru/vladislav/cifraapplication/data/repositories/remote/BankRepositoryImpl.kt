package ru.vladislav.cifraapplication.data.repositories.remote

import io.reactivex.Single
import retrofit2.Response
import ru.vladislav.cifraapplication.data.model.Bank
import ru.vladislav.cifraapplication.data.source.remote.RetrofitService
import ru.vladislav.cifraapplication.domain.repositories.IBankRepository
import javax.inject.Inject


class BankRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) :
    IBankRepository {
    override fun getBankList(): Single<Response<List<Bank>>> {
        return retrofitService.instance?.getBankList()
            ?: Single.error(Exception("Instance is null"))
    }
}