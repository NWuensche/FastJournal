package journal.app.niklas.a5minutejournal

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by nwuensche on 22.02.17.
 */
object Design {

    fun setEditTabTitle(activity: Activity) {
        val date: String = FileName.getCurrFileDate()

        if(date.toUpperCase().equals("TODAY")) {
            activity.tabs.getTabAt(1)!!.text = "TODAY"
            return
        }

        activity.tabs.getTabAt(1)!!.text = date
    }

}