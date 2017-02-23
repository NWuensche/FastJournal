package journal.app.niklas.a5minutejournal

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tabs.*

/**
 * Created by nwuensche on 22.02.17.
 */
object Design {

    fun setEditTabTitle(activity: Activity) {
        val date: String = FileName.getCurrFileDateOrToday()

        if(date.toUpperCase().equals("TODAY")) {
            activity.tabs.getTabAt(1)!!.text = "TODAY"
            return
        }

        activity.tabs.getTabAt(1)!!.text = date

        activity.layout_today.scrollTo(0, activity.section_grateful_label.bottom - activity.section_grateful_label.height)

    }

}