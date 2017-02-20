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

        val filename = Today.getToday().replace(" ", "_").plus(".txt")
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

        val input = "$grateful$great$daily"

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

        val great = "$daily1$daily2\n"

        return great
    }

}