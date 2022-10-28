package ru.vladislav.cifraapplication.di

import dagger.Module
import ru.vladislav.cifraapplication.di.modules.RepositoryModule
import ru.vladislav.cifraapplication.di.modules.RetrofitModule

@Module(
    includes = [
        RetrofitModule::class,
        RepositoryModule::class
    ]
)
open class AppModule