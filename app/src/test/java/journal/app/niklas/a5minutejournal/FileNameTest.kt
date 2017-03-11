package journal.app.niklas.a5minutejournal

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by nwuensche on 11.03.17.
 */
class FileNameTest {

    @Test
    fun isStartFileNameToday() {
        FileName.setCurrentFileDate("") // startCondition
        assertThat(FileName.getCurrFileDateOrToday(), `is`("TODAY"))
        assertThat(FileName.getRealDate(), `is`(TestTodayHelper.today()))
    }

}