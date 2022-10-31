package ru.vladislav.cifraapplication.di

import dagger.Module
import ru.vladislav.cifraapplication.di.modules.RepositoryModule
import ru.vladislav.cifraapplication.di.modules.RetrofitModule
import ru.vladislav.cifraapplication.di.modules.UseCaseModule

@Module(
    includes = [
        RetrofitModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
open class AppModule