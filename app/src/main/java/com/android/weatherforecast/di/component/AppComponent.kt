package com.android.weatherforecast.di.component

import android.app.Application
import com.android.weatherforecast.ForecastApplication
import com.android.weatherforecast.di.module.WeatherApiModule
import com.android.weatherforecast.di.module.WeatherRoomModule
import com.android.weatherforecast.di.builder.ActivityBuilder
import com.android.weatherforecast.di.module.ViewModelFactoryModule
import com.android.weatherforecast.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        WeatherRoomModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        WeatherApiModule::class,
        ActivityBuilder::class

    ]
)
interface AppComponent : AndroidInjector<ForecastApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: ForecastApplication)
}