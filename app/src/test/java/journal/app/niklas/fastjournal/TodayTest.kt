package journal.app.niklas.fastjournal

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by nwuensche on 11.03.17.
 */
class TodayTest {

    @Test
    fun getDate() {
        assertThat(Today.getDate(), `is`(TestTodayHelperJUnit.today()))
    }

    @Test
    fun getTodayFileName() {
        assertThat(Today.getTodayFileName(), `is`(TestTodayHelperJUnit.today().replace(" ", "_").plus(".txt")))
    }

}