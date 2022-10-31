package ru.vladislav.cifraapplication.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.vladislav.cifraapplication.domain.useCase.GetBankListUseCase
import ru.vladislav.cifraapplication.presentation.viewModel.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    //viewModels
    fun inject(viewModel: MainViewModel)

    //UseCases
    fun inject(useCase: GetBankListUseCase)

}