package com.android.weatherforecast.ui.fivedayactivity


import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.weatherforecast.R
import com.android.weatherforecast.app_utils.*
import com.android.weatherforecast.app_utils.Constants.API_KEY
import com.android.weatherforecast.databinding.MainActivityBinding
import com.android.weatherforecast.location.LocationProvider
import com.android.weatherforecast.location.LocationProvider.Companion.MY_PERMISSIONS_REQUEST_LOCATION
import com.android.weatherforecast.models.FiveDaysWeather
import com.android.weatherforecast.models.ItemHourly
import com.android.weatherforecast.ui.BaseActivity
import com.android.weatherforecast.ui.fivedayactivity.adapter.FiveDayListAdapter
import com.android.weatherforecast.ui.selection_activity.SelectionActivity
import kotlinx.android.synthetic.main.five_days_content.*
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*


class FiveDayWeatherActivity : BaseActivity<FiveDaysWeatherViewModel, MainActivityBinding>(), LocationProvider.MyLocationListener,FiveDayListAdapter.OnItemClickListener {


    override fun getViewBinding(): MainActivityBinding = MainActivityBinding.inflate(layoutInflater)

    override fun getViewModel() = viewModelOf<FiveDaysWeatherViewModel>(mViewModelProvider)

    override fun onItemClicked(post: FiveDaysWeather, view: View) {
       showToast("ItemHourly")
    }

    //we can also Inject LocationProvider through Dagger
    var mLocationProvider = LocationProvider()

    private val mAdapter: FiveDayListAdapter by lazy { FiveDayListAdapter(onItemClickListener = this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        initUI()

        var cityName=intent.extras?.getString(Constants.CITY_NAME)

        if(cityName==null) {
            mLocationProvider.startListeningUserLocation(this, this)
        }else{
            val lat=intent.extras?.getDouble(Constants.CITY_LAT)
            val lon=intent.extras?.getDouble(Constants.CITY_LON)

            //mViewModel.getFiveDaysWeather(cityName,Constants.UNITS,Constants.Lang,5,Constants.API_KEY)
            mViewModel.getFiveDaysWeatherWithPos(q=cityName,lang = Constants.Lang,units = Constants.UNITS,lat = lat.toString(),lon=lon.toString(),dayCount = 5,appId = API_KEY)

            toolbar_title.title=cityName

            var weatherId= intent.extras?.getInt(Constants.WEATHER_ID)
            var humidity= intent.extras?.getInt(Constants.HUMIDITY)
            var temprature= intent.extras?.getDouble(Constants.TEMPERATURE)
            var wind= intent.extras?.getDouble(Constants.WIND)


            setData(weatherId,humidity,temprature,wind)
        }

        observeData()
    }


    private fun initUI(){

        // Initialize RecyclerView
        mViewBinding.content.recyclerView.apply {
            adapter = mAdapter
        }

        addLocationButton.setOnClickListener {

            val nextIntent= Intent(this,SelectionActivity::class.java)
            startActivity(nextIntent)
            finish()
        }

    }

    fun setData(weatherId:Int?,humidity:Int?,temprature:Double?,wind:Double?){


        temp_text_view.setText(

                    temprature.toString()

            )
            description_text_view.setText(
                AppUtil.getWeatherStatus(
                    weatherId!!,
                    AppUtil.isRTL(this)
                )
            )

        weatherId?.let {

            AppUtil.setWeatherIcon(
                this, weather_image_view,
                it
            )
        }




        humidity_text_view.setText(
                String.format(
                    Locale.getDefault(),
                    "%d%%",
                    humidity
                )
            )
        wind_text_view.setText(
                String.format(
                    Locale.getDefault(),
                    resources.getString(R.string.wind_unit_label),
                    wind
                )
            )
        val calendar =
            Calendar.getInstance(TimeZone.getDefault())
        var hourOfDay=calendar.get(Calendar.HOUR_OF_DAY)

        fiveDaysTV.append((" = "+hourOfDay).toString())

    }

    override fun onLocationChanged(location: Location) {

        mViewModel.getFiveDaysWeatherWithPos(q="",lang = Constants.Lang,units = Constants.UNITS,lat = location.latitude.toString(),lon=location.longitude.toString(),dayCount = 5,appId = API_KEY)
    }


    private fun  observeData(){

        mViewModel.postsLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data!=null) {
                        toolbar_title.title=state.data.city?.name
                        mAdapter.submitList(arrangeDataForEachDay(state.data))
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    showError(state.message)
                    showLoading(false)
                }
            }
        })


    }


    private fun arrangeDataForEachDay(fiveDaysWeather: FiveDaysWeather): MutableList<FiveDaysWeather> {

       var dataMap= fiveDaysWeather.list?.groupBy {

           it.dt_txt?.substring(0,11)
        }

        var daysData = mutableListOf<FiveDaysWeather>()

        dataMap?.forEach {

            var tempObj=FiveDaysWeather()
            tempObj.city=fiveDaysWeather.city
            tempObj.cnt =fiveDaysWeather.cnt
            tempObj.cod =fiveDaysWeather.cod
            tempObj.dt =fiveDaysWeather.dt
            tempObj.dtTxt =fiveDaysWeather.dtTxt
            tempObj.id =fiveDaysWeather.id
            tempObj.message =fiveDaysWeather.message
            tempObj.list =it.value

            daysData.add(tempObj)
        }

        return daysData

   }
    private fun showLoading(isLoading: Boolean) {
        if(isLoading)
            mViewBinding.progressLoader.visibility= View.VISIBLE
        else
            mViewBinding.progressLoader.visibility= View.GONE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                mLocationProvider.startListeningUserLocation(this, this)
            }
        }
    }




}


