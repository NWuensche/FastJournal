package journal.app.niklas.a5minutejournal

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.*

import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Created by nwuensche on 10.03.17.
 */
class DateStringTest {

    @Test
    fun compareRight() {
        val first: DateString = DateString("23 February 2013")
        val second: DateString = DateString("24 February 2013")
        val third: DateString = DateString("22 March 2013")
        val forth: DateString = DateString("21 July 2014")

        assertThat(first.compareTo(second), `is`(lessThan(0)))
        assertThat(third.compareTo(first), `is`(greaterThan(0)))
        assertThat(second.compareTo(forth), `is`(lessThan(0)))

    }
}