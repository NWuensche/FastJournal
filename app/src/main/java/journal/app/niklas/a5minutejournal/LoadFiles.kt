package journal.app.niklas.a5minutejournal

import android.app.Activity
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_tabs.*
import java.io.File
import java.io.FileInputStream

/**
 * Created by nwuensche on 19.02.17.
 */
object LoadFiles {

    fun getAllDateFileNames(activity: Activity): List<String> {
        return activity.filesDir
                .listFiles()
                .map { it.name }
                .filter { Character.isDigit(it[0]) }
                .sorted()
        // 9 Feb -> 09 February
    }

    fun getTextFromFile(activity: Activity, fileName: String): String {
        var buffer = ByteArray(0)

        try {
            val file = File(activity.baseContext.filesDir, fileName)
            buffer = ByteArray(file.length().toInt())

            val inputStream: FileInputStream = FileInputStream(file)
            inputStream.read(buffer)
            inputStream.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

        return String(buffer)
    }

    fun loadTodaysTextFromFileToView(activity: Activity) {
        if(!doesTodaysExist(activity)) { //TODO Brauch ich das überhaupt?
            return
        }

        val lines = getTextFromFile(activity, Today.getTodayFileName()).split("\n")

        fillTextViews(lines.iterator(), activity)
    }

    private fun doesTodaysExist(activity: Activity): Boolean {
        return getAllDateFileNames(activity).contains(Today.getToday().replace(" ", "_").plus(".txt"))
    }

    private fun fillTextViews(lines: Iterator<String>, activity: Activity) {
        activity.editText_grateful1.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_grateful2.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_grateful3.setText(lines.next(), TextView.BufferType.EDITABLE)
        lines.next() // Empty line to seperate parts
        activity.editText_great1.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_great2.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_great3.setText(lines.next(), TextView.BufferType.EDITABLE)
        lines.next()
        activity.editText_daily1.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_daily2.setText(lines.next(), TextView.BufferType.EDITABLE)
        lines.next()
        activity.editText_happend1.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_happend2.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_happend3.setText(lines.next(), TextView.BufferType.EDITABLE)
        lines.next()
        activity.editText_better1.setText(lines.next(), TextView.BufferType.EDITABLE)
        activity.editText_better2.setText(lines.next(), TextView.BufferType.EDITABLE)
        lines.next()
    }

}