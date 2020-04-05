package com.android.weatherforecast.ui.selection_activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.android.weatherforecast.R
import com.android.weatherforecast.app_utils.Constants.CITY_ID
import com.android.weatherforecast.app_utils.Constants.CITY_LAT
import com.android.weatherforecast.app_utils.Constants.CITY_LON
import com.android.weatherforecast.app_utils.Constants.CITY_NAME
import com.android.weatherforecast.app_utils.Constants.HUMIDITY
import com.android.weatherforecast.app_utils.Constants.TEMPERATURE
import com.android.weatherforecast.app_utils.Constants.WEATHER_ID
import com.android.weatherforecast.app_utils.Constants.WIND
import com.android.weatherforecast.app_utils.State
import com.android.weatherforecast.app_utils.getColorRes
import com.android.weatherforecast.app_utils.showToast
import com.android.weatherforecast.app_utils.viewModelOf
import com.android.weatherforecast.data.shared_prefrences.CitiesStringPreference
import com.android.weatherforecast.databinding.ActivitySelectionBinding
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.ui.BaseActivity
import com.android.weatherforecast.ui.fivedayactivity.FiveDayWeatherActivity
import com.android.weatherforecast.ui.selection_activity.adapter.CityListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_selection.*


class SelectionActivity : BaseActivity<CurrentWeatherViewModel, ActivitySelectionBinding>(),
    CityListAdapter.OnItemClickListener {


    override fun getViewBinding(): ActivitySelectionBinding {
        return ActivitySelectionBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): CurrentWeatherViewModel {

        return viewModelOf<CurrentWeatherViewModel>(mViewModelProvider)
    }

    override fun onItemClicked(currentWeather: CurrentWeather, view: View) {

        //we can also do this by sending whole object through parcelable
        val nextIntent = Intent(this, FiveDayWeatherActivity::class.java)
        nextIntent.putExtra(CITY_ID, currentWeather.id)
        nextIntent.putExtra(CITY_NAME, currentWeather.name)
        nextIntent.putExtra(CITY_LAT, currentWeather.coord?.lat)
        nextIntent.putExtra(CITY_LON, currentWeather.coord?.lon)
        nextIntent.putExtra(WEATHER_ID, currentWeather.weather?.get(0)?.id)
        nextIntent.putExtra(HUMIDITY, currentWeather.main?.humidity)
        nextIntent.putExtra(TEMPERATURE, currentWeather.main?.temp)
        nextIntent.putExtra(WIND, currentWeather.wind?.speed)
        startActivity(nextIntent)
        finish()
    }

    private val mAdapter: CityListAdapter by lazy { CityListAdapter(onItemClickListener = this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        floatingAddButton.setOnClickListener {

            showInputDialogue()
        }

        initUI()

        observeData()

        mViewModel.getWeatherData()
    }


    private fun initUI() {

        // Initialize RecyclerView
        mViewBinding.citiesRV.apply {
            adapter = mAdapter
        }

    }


    private fun observeData() {
        mViewModel._currentWeatherLiveDataList.observe(this, Observer { state ->

            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data != null) {
                        showLoading(false)
                        mAdapter.submitList(state.data)
                    }
                }
                is State.Error -> {
                    showError(state.message)
                    showLoading(false)
                }
            }

        })
    }


    private fun showLoading(isLoading: Boolean) {

        if (isLoading)
            mViewBinding.progressLoader.visibility = VISIBLE
        else
            mViewBinding.progressLoader.visibility = GONE

    }


    private fun showInputDialogue() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_title))
        builder.setMessage(getString(R.string.dialog_text))

        val view = layoutInflater.inflate(R.layout.input_dialogue_layout, null)

        val input = view.findViewById(R.id.citiesInputEditText) as EditText

        builder.setView(view)


        var citiesName =CitiesStringPreference(this@SelectionActivity).citiesString //

        input.setText(citiesName)

        builder.setPositiveButton(
            getString(R.string.done_button),
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    runOnUiThread {

                        val tempQuery = input.text.toString()

                        if (mViewModel.isValidDataEnteredByUser(tempQuery)) {
                            CitiesStringPreference(this@SelectionActivity).citiesString = tempQuery
                            mViewModel.getCurrentWeatherNew(mViewModel.mapToList(tempQuery))
                        } else {
                            showError(getString(R.string.invalid_data_message))
                        }

                    }


                }
            })
        builder.setNeutralButton(
            getString(R.string.cancel_button),
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.cancel()
                }
            })

        val alert11: AlertDialog = builder.create()
        alert11.show()


        val buttonbackground: Button =
            alert11.getButton(DialogInterface.BUTTON_NEUTRAL)
        buttonbackground.setBackgroundColor(Color.RED)

        val buttonbackground1: Button =
            alert11.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonbackground1.setBackgroundColor(getColorRes(R.color.done_color))

        buttonbackground1.setTextColor(Color.WHITE)
        buttonbackground.setTextColor(Color.WHITE)

    }


}
