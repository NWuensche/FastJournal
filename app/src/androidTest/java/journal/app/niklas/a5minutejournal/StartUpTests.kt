package journal.app.niklas.a5minutejournal

import android.support.design.widget.TabLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import junit.framework.Assert.fail
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by nwuensche on 11.03.17.
 */
class StartUpTests : SuperEspresso() {

    @Test
    fun isTodayTabOpenAfterStart() {
        // Edit Layout visible
        onView(allOf(isDisplayed(), withId(R.id.layout_today))).check(matches(isDisplayed()))


        // Other Layout invisible
        try {
            onView(allOf(isDisplayed(), withId(R.id.all_entries_view))).check(matches(not(isDisplayed())))
            fail()
        }
        catch(e: NoMatchingViewException) {
        }
    }

    @Test
    fun areTabTitlesRight() {
        onView(withId(R.id.tabs)).check { view, noViewFoundException ->
            val tabs = view as TabLayout
            assertThat(tabs.getTabAt(0)!!.text.toString(), `is`("ALL ENTRIES"))
            assertThat(tabs.getTabAt(1)!!.text.toString(), `is`("TODAY"))
        }
    }

}