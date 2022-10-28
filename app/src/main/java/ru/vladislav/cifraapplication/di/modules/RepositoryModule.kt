package ru.vladislav.cifraapplication.di.modules

import dagger.Module
import dagger.Provides
import ru.vladislav.cifraapplication.data.retrofit.services.RetrofitService
import ru.vladislav.cifraapplication.repositories.IBankRepository
import ru.vladislav.cifraapplication.repositories.implementation.BankRepositoryImpl

@Module
open class RepositoryModule {

    @Provides
    fun provideBankRepository(retrofitService: RetrofitService): IBankRepository =
        BankRepositoryImpl(retrofitService)
}