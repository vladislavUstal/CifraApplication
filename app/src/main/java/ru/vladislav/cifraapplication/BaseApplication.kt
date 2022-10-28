package ru.vladislav.cifraapplication

import android.app.Application
import ru.vladislav.cifraapplication.di.AppComponent
import ru.vladislav.cifraapplication.di.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BaseApplication
        fun get(): BaseApplication {
            return instance
        }
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
}