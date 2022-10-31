package ru.vladislav.cifraapplication.di.modules

import dagger.Module
import dagger.Provides
import ru.vladislav.cifraapplication.data.repositories.remote.BankRepositoryImpl
import ru.vladislav.cifraapplication.domain.useCase.GetBankListUseCase

@Module
open class UseCaseModule {

    @Provides
    fun provideGetBankListUseCase(repository: BankRepositoryImpl) = GetBankListUseCase(repository)
}