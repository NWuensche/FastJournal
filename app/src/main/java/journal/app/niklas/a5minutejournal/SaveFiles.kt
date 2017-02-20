package journal.app.niklas.a5minutejournal

import android.content.Context
import android.util.Log
import journal.app.niklas.a5minutejournal.LoadFiles.getAllDateFileNames
import kotlinx.android.synthetic.main.fragment_today.*
import java.io.FileOutputStream


/**
 * Created by nwuensche on 19.02.17.
 */
object SaveFiles {

    private var activity: MainActivity? = null

    fun onSaveToday(mainActivity: MainActivity) {
        activity = mainActivity

        val filename = Today.getTodayFileName()
        val input = getAllInput()

        try {
            val outputStream: FileOutputStream = activity!!.openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(input.toByteArray())
            outputStream.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

        getAllDateFileNames(activity!!)
                .forEach { Log.e("test", "Filename: $it:\n".plus(LoadFiles.getTextFromFile(activity!!, it))) }
    }

    private fun getAllInput(): String {
        val grateful: String = getGrateful()
        val great: String = getGreat()
        val daily: String = getDaily()
        val happend: String = getHappend()
        val better: String = getBetter()

        val input = "$grateful$great$daily$happend$better"

        return input
    }

    private fun getGrateful(): String {
        val grateful1 = activity!!.editText_grateful1.text.toString().plus('\n')
        val grateful2 = activity!!.editText_grateful2.text.toString().plus('\n')
        val grateful3 = activity!!.editText_grateful3.text.toString().plus('\n')

        val grateful = "$grateful1$grateful2$grateful3\n"

        return grateful
    }

    private fun getGreat(): String {
        val great1 = activity!!.editText_great1.text.toString().plus('\n')
        val great2 = activity!!.editText_great2.text.toString().plus('\n')
        val great3 = activity!!.editText_great3.text.toString().plus('\n')

        val great = "$great1$great2$great3\n"

        return great
    }

    private fun getDaily(): String {
        val daily1 = activity!!.editText_daily1.text.toString().plus('\n')
        val daily2 = activity!!.editText_daily2.text.toString().plus('\n')

        val daily = "$daily1$daily2\n"

        return daily
    }

    private fun getHappend(): String {
        val happend1 = activity!!.editText_happend1.text.toString().plus('\n')
        val happend2 = activity!!.editText_happend2.text.toString().plus('\n')
        val happend3 = activity!!.editText_happend3.text.toString().plus('\n')

        val happend = "$happend1$happend2$happend3\n"

        return happend
    }

    private fun getBetter(): String {
        val better1 = activity!!.editText_better1.text.toString().plus('\n')
        val better2 = activity!!.editText_better2.text.toString().plus('\n')

        val better = "$better1$better2"

        return better
    }

}