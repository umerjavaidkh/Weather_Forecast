package com.android.weatherforecast.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.weatherforecast.R
import com.android.weatherforecast.app_utils.Constants
import com.android.weatherforecast.ui.fivedayactivity.FiveDayWeatherActivity
import com.android.weatherforecast.ui.selection_activity.SelectionActivity

class SplashActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_splash)


        val nextIntent= Intent(this, SelectionActivity::class.java)
        startActivity(nextIntent)
        finish()
    }



}
