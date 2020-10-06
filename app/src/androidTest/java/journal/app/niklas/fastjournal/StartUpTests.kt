package journal.app.niklas.fastjournal

import com.google.android.material.tabs.TabLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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


        // ALl Entries Layout invisible
        try {
            onView(allOf(isDisplayed(), withId(R.id.all_entries_view))).check(matches(not(isDisplayed())))
            fail()
        }
        catch(e: NoMatchingViewException) {
        }
    }

    @Test
    fun areTabTitlesRight() {
        onView(withId(R.id.tabs)).check { view, _ ->
            val tabs = view as TabLayout
            assertThat(tabs.getTabAt(0)!!.text.toString(), `is`("ALL ENTRIES"))
            assertThat(tabs.getTabAt(1)!!.text.toString(), `is`("TODAY"))
        }
    }

}