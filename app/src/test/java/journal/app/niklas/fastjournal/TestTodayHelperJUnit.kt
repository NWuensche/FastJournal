package journal.app.niklas.fastjournal

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nwuensche on 11.03.17.
 */
object TestTodayHelperJUnit {

    fun today(): String {
        return SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
                .format(Calendar.getInstance().time)
    }

}