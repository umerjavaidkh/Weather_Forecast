package com.android.weatherforecast

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.android.weatherforecast.ui.SplashActivity
import com.android.weatherforecast.ui.selection_activity.SelectionActivity
import com.android.weatherforecast.utils.MyViewAction
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@LargeTest
class AppJourney {

    /**
     * Set the rule to launch the activity.
     */
    @get:Rule
    val mActivityRule1 = ActivityTestRule(SplashActivity::class.java)


    @Rule
    @JvmField
    val grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Before
    fun setup() {

    }


    @Test
    fun startSelectionActivity() {

        val mActivityRule2 = ActivityTestRule(SelectionActivity::class.java)

        mActivityRule2.launchActivity(null)
    }


    fun addButtonDisplayed() {
        onView(withId(R.id.floatingAddButton)).check(matches(isDisplayed()))
    }

    fun clickAddButton() {
        onView(withId(R.id.floatingAddButton)).perform(click())
    }

    val tempStr = "Sharjah,Dubai,Ajman,Rawalpindi,Rawalakot"

    val tempStr1 = "Dubai,Sharjah city,Ajman,"

    @Test
    fun isInputCitiesDialogDisplays() {


        //check for add floating button
        addButtonDisplayed()

        //click on the add floating button
        clickAddButton()


        //check if add cities dialog displays

        onView(withText(R.string.dialog_title))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))

        //check if user already entered the cities if not then add else cancel
        if (!viewIsDisplayed(tempStr1)) {

            onView(withId(R.id.citiesInputEditText)).perform(
                typeText(tempStr),
                pressImeActionButton()
            )

            onView(withText(R.string.done_button))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())

            Thread.sleep(7000)

        } else {

            onView(withText(R.string.cancel_button))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click())

            Thread.sleep(2000)


        }


        // after adding cities launch last activity with 5/3 weather report

        Intents.init()

        onView(withId(R.id.citiesRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.clickButtonParent)))

        Thread.sleep(7000)


        // add or edit more cities

        Espresso.onView(ViewMatchers.withId(R.id.addLocationButton)).perform(ViewActions.click())
        Intents.release();


    }


    fun viewIsDisplayed(str: String): Boolean {
        val isDisplayed = booleanArrayOf(true)
        onView(withText(str)).withFailureHandler { error, viewMatcher -> isDisplayed[0] = false }
            .check(matches(isDisplayed()))
        return isDisplayed[0]
    }


}