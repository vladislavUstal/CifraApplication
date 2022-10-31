package ru.vladislav.cifraapplication.domain.useCase.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class SingleUseCase<T> : UseCase() {

    internal abstract fun buildUseCaseSingle(vararg args: String?): Single<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {},
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle()
            .retryWhen { completed -> completed.take(500).delay(5, TimeUnit.SECONDS) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}