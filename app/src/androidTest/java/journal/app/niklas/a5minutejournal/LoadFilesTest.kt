package journal.app.niklas.a5minutejournal

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_tabs.*
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Test


/**
 * Created by nwuensche on 11.03.17.
 */
class LoadFilesTest : SuperEspresso() {

    val contentToday: String = "Test\n\n\n\n\n\n\n\n\nHere\n\n\n\n\n\n\n\n"

    @Test
    fun todayRight() {
        setUpFiles()
        Assert.assertThat(LoadFiles.getTextFromFile(appContext, TestTodayHelperEspresso.today()), `is`(contentToday))
        onView(allOf(isDisplayed(), withId(R.id.editText_grateful1))).check(matches(withText("Test")))
    }

    fun setUpFiles() {
        removeAllFiles()
        addFiveFiles()
    }

    private fun removeAllFiles() {
        appContext.filesDir
                .deleteRecursively()
    }

    private fun addFiveFiles() {
        val date1: String = "1 January 2013"
        val content1: String = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"

        val date2: String = "2 June 2013"
        val content2: String = "a\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\nn\no\np\nq\n"

        val date3: String = "1 October 2015"
        val content3: String = "1\n2\n3\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"

        val date4: String = "1 November 2015"
        val content4: String = "\n\n\n\n\n\n\n\n\n\n\n\n\n\nx\ny\nz\n"

        val dateToday: String = TestTodayHelperEspresso.today()
        val contentToday: String = "Test\n\n\n\n\n\n\n\n\nHere\n\n\n\n\n\n\n\n"

        SaveFiles.saveFile(appContext, date1, content1)
        SaveFiles.saveFile(appContext, date2, content2)
        SaveFiles.saveFile(appContext, date3, content3)
        SaveFiles.saveFile(appContext, date4, content4)
        SaveFiles.saveFile(appContext, dateToday, contentToday)
    }

    /**
     * @ImplNotes You might have to unistall app from device
     */
    @Test
    fun fiveEntriesInAllEntries() {
        setUpFiles()
        onView(withText("ALL ENTRIES")).perform(click())
        checkIfAllEntriesTabOpened()
        checkIfFiveEntries()
    }

    private fun checkIfAllEntriesTabOpened() {
        // All Entries Layout visible
        onView(allOf(isDisplayed(), withId(R.id.all_entries_view))).check(matches(isDisplayed()))


        // Edit Layout invisible
        try {
            onView(allOf(isDisplayed(), withId(R.id.layout_today))).check(matches(CoreMatchers.not(isDisplayed())))
            junit.framework.Assert.fail()
        }
        catch(e: NoMatchingViewException) {
        }
    }

    private fun checkIfFiveEntries() {
        onView(allOf(isDisplayed(), withId(R.id.all_entries_view))).check { view, _ ->
            val list = view as ListView

            assertThat(list.adapter.count, `is`(5))

            val firstItem = list.getItemAtPosition(0) as String
            Assert.assertThat(firstItem, `is`("Today"))
        }
    }

    @Test
    fun loadRightStuffFromFile() {
        onView(withText("ALL ENTRIES")).perform(click())
        onView(withText("2 June 2013")).perform(click())

        checkIfEditTabOpened()
        onView(allOf(isDisplayed(), withId(R.id.editText_grateful3))).check(matches(withText("c")))

        scrollDown()// Necessary because listview doesnt build what is not on screen
        onView(allOf(isDisplayed(), withId(R.id.editText_better1))).check(matches(withText("p")))
    }

    private fun checkIfEditTabOpened() {
        // Edit Layout invisible
        onView(allOf(isDisplayed(), withId(R.id.layout_today))).check(matches(isDisplayed()))


        // All Entries Layout visible
        try {
            onView(allOf(isDisplayed(), withId(R.id.all_entries_view))).check(matches(not(isDisplayed())))
            junit.framework.Assert.fail()
        }
        catch(e: NoMatchingViewException) {
        }
    }

    private fun scrollDown() {
        activityRule.activity.layout_today.scrollTo(0, 10*activityRule.activity.editText_better2.bottom)
    }

}
