package ru.vladislav.cifraapplication.di.modules

import dagger.Module
import dagger.Provides
import ru.vladislav.cifraapplication.data.repositories.remote.BankRepositoryImpl
import ru.vladislav.cifraapplication.data.source.remote.RetrofitService
import ru.vladislav.cifraapplication.domain.repositories.IBankRepository

@Module
open class RepositoryModule {

    @Provides
    fun provideBankRepository(retrofitService: RetrofitService): IBankRepository =
        BankRepositoryImpl(retrofitService)
}