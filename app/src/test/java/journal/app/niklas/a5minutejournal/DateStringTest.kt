package journal.app.niklas.a5minutejournal

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.lessThan

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by nwuensche on 10.03.17.
 */
class DateStringTest {

    @Test
    fun compareRight() {
        val first: DateString = DateString("23 January 2013")
        val second: DateString = DateString("24 January 2013")

        assertThat(first.compareTo(second), `is`(lessThan(0)))
    }
}