package journal.app.niklas.a5minutejournal

import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

/**
 * Created by nwuensche on 19.02.17.
 */
object Today {

    fun getDate(): String {
        val cal: Calendar = Calendar.getInstance()
        //cal.set(2017, 1, 1)
        val today: Date = cal.time
        return SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(today.time)
    }

    fun getTodayFileName(): String {
        return FileName.convertDateToFileName(getDate())
    }

}