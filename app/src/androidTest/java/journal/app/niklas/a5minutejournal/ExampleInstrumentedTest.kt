package journal.app.niklas.a5minutejournal

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleInstrumentedTest : SuperEspresso() {

    @Before
    fun deleteAllFiles() {
        appContext.filesDir
                .listFiles()
                .forEach { this::removeFile }
    }

    fun removeFile(fileName: String) {
        val file = File(appContext.filesDir, fileName)
        file.delete()
    }

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        assertEquals("journal.app.niklas.a5minutejournal", appContext.packageName)
    }

    @Test
    fun writingWorks() {
        onView(allOf(isDisplayed(),withId(R.id.editText_grateful1))).perform(typeText("Hello"))
        onView(allOf(isDisplayed(),withId(R.id.editText_grateful1))).check(matches(withText("Hello")))
    }

}
