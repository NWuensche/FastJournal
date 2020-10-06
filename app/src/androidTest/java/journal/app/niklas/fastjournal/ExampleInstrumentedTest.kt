package journal.app.niklas.fastjournal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
        assertEquals("journal.app.niklas.fastjournal", appContext.packageName)
    }

    @Test
    fun writingWorks() {
        onView(allOf(isDisplayed(),withId(R.id.editText_grateful2))).perform(typeText("Hello"))
        onView(allOf(isDisplayed(),withId(R.id.editText_grateful2))).check(matches(withText("Hello")))
    }

}
