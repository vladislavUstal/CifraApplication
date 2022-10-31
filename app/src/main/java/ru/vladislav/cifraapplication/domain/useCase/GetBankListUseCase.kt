package ru.vladislav.cifraapplication.domain.useCase

import io.reactivex.Single
import retrofit2.Response
import ru.vladislav.cifraapplication.BaseApplication
import ru.vladislav.cifraapplication.data.model.Bank
import ru.vladislav.cifraapplication.domain.repositories.IBankRepository
import ru.vladislav.cifraapplication.domain.useCase.base.SingleUseCase
import javax.inject.Inject

class GetBankListUseCase @Inject constructor(private var repository: IBankRepository) :
    SingleUseCase<Response<List<Bank>>>() {

    init {
        BaseApplication.get().appComponent.inject(this)
    }

    override fun buildUseCaseSingle(vararg args: String?): Single<Response<List<Bank>>> {
        return repository.getBankList()
    }
}