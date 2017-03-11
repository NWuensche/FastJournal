package journal.app.niklas.a5minutejournal

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*

/**
 * Created by nwuensche on 11.03.17.
 */
class FileNameTest {

    @Test
    fun isStartFileNameToday() {
        FileName.setCurrentFileDate("") // startCondition, don't know if first test

        assertThat(FileName.getCurrFileDateOrToday(), `is`("TODAY"))
        assertThat(FileName.getRealDate(), `is`(TestTodayHelper.today()))
    }

    @Test
    fun ownDate() {
        val date: String = "26 December 2016"
        val fileName: String = "26_December_2016.txt"

        FileName.setCurrentFileDate(date)

        assertThat(FileName.getCurrFileDateOrToday(), `is`(date))
        assertThat(FileName.getRealDate(), `is`(date))
        
        assertThat(FileName.convertDateToFileName(date), `is`(fileName))
        assertThat(FileName.convertFileNameToRealDate(fileName), `is`(date))
    }

    @Test
    fun filterTodayFromFileNames() {
        val fileNames: List<String> = Arrays.asList("12 January 2013", "23 October 2015",
                TestTodayHelper.today())

        val filteredNames = fileNames
                .filter { name -> FileName.filterRemoveTodayDate(name)}

        assertThat(filteredNames, `is`(Arrays.asList("12 January 2013", "23 October 2015")))
    }

}