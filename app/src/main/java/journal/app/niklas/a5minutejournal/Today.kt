package journal.app.niklas.a5minutejournal

import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

/**
 * Created by nwuensche on 19.02.17.
 */
object Today {

    fun getDate(): String {
        val today: Date = Calendar.getInstance().time
        return SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(today.time)
    }

    fun getTodayFileName(): String {
        return getDate().replace(" ", "_").plus(".txt")
    }

}