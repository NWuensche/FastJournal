package journal.app.niklas.a5minutejournal

import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nwuensche on 11.03.17.
 */
class TodayTest {

    val today: String = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
            .format(Calendar.getInstance().time)

    @Test
    fun getDate() {
        assertThat(today, `is`(Today.getDate()))
    }

    @Test
    fun getTodayFileName() {
        assertThat(today.replace(" ", "_").plus(".txt"), `is`(Today.getTodayFileName()))
    }

}