package journal.app.niklas.a5minutejournal

import android.content.Context
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
                .forEach { println("Filename: $it:\n".plus(LoadFiles.getTextFromFile(activity!!, it))) }
    }

    private fun getAllInput(): String {
        val grateful: String = getGrateful()

        val input = grateful

        return input
    }

    private fun getGrateful(): String {
        val grateful1 = activity!!.editText_grateful1.text.toString().plus('\n')

        val grateful = grateful1.plus("\n")

        return grateful
    }

}