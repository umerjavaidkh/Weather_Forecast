package com.android.weatherforecast.data.shared_prefrences

import android.content.Context

class CitiesStringPreference(context: Context) : Preferences(context,"Cities") {

    var citiesString by stringPref()

}