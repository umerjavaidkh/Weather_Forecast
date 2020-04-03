package com.android.weatherforecast.di.builder

import com.android.weatherforecast.ui.fivedayactivity.FiveDayWeatherActivity
import com.android.weatherforecast.ui.selection_activity.SelectionActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector
    abstract fun bindMainActivity(): FiveDayWeatherActivity

    @ContributesAndroidInjector
    abstract fun bindSelectionActivity(): SelectionActivity

}