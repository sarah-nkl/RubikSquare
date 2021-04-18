package com.motional.rubiksquare

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickAddP2_AddsSecondFragment() {
        // Given that there is only one player at the start

        // When the "Add Player" button is clicked
        onView(withId(R.id.fab)).perform(click())

        // Then the second fragment should be displayed
        onView(withId(R.id.second_fragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickRemoveP2_RemovesSecondFragment() {
        // Given that there are two players
        onView(withId(R.id.fab)).perform(click())

        // When the "Remove Player" button is clicked
        onView(withId(R.id.fab)).perform(click())

        // Then the second fragment should be gone
        onView(withId(R.id.second_fragment))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun clickP2Cell_DisablesClickOnP2Cell() {
        // Given that there are two players
        onView(withId(R.id.fab)).perform(click())

        // When the P2 taps on a cell
        onView(withId(R.id.rv_second_square)).perform(click())

        // Then the square for P2 will be disabled
        onView(withId(R.id.second_touch_blocker))
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickEndGame_DialogAppears() {
        // When end game is selected
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.action_end_game)).perform(click())

        // Then Dialog appears
        onView(withText(R.string.completion_dialog_positive_text))
            .check(matches(isDisplayed()))
    }
}