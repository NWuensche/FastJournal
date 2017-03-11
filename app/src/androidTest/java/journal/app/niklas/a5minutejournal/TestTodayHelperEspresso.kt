package journal.app.niklas.a5minutejournal

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nwuensche on 11.03.17.
 */
object TestTodayHelperEspresso {

    fun today(): String {
        return SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
                .format(Calendar.getInstance().time)
    }

}