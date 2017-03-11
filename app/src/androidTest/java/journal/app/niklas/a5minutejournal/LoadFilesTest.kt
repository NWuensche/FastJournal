package journal.app.niklas.a5minutejournal

import android.content.Context
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import journal.app.niklas.a5minutejournal.TestTodayHelperEspresso
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.app.AlarmManager
import android.app.PendingIntent





/**
 * Created by nwuensche on 11.03.17.
 */
class LoadFilesTest : SuperEspresso() {

    val contentToday: String = "Test\n\n\n\n\n\n\n\n\nHere\n\n\n\n\n\n\n\n"

    @Before
    fun addFiles() {
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

        val content = mapOf(date1 to content1, date2 to content2, date3 to content3,
                date4 to content4, dateToday to contentToday)

        content.forEach { date, content -> SaveFiles.saveFile(appContext, date, content)}

    }

    @Test
    fun TodayRight() {
        Assert.assertThat(LoadFiles.getTextFromFile(appContext, TestTodayHelperEspresso.today()), `is`(contentToday))
        onView(allOf(isDisplayed(), withId(R.id.editText_grateful1))).check(matches(withText("Test")))
    }

}