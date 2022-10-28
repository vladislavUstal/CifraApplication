package ru.vladislav.cifraapplication.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.vladislav.cifraapplication.ui.main.MainViewModel
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


    fun inject(viewModel: MainViewModel)

}