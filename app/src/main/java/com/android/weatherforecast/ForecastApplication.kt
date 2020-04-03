package com.android.weatherforecast

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.android.weatherforecast.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ForecastApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()

        // Initialize Dependency Injection
        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}