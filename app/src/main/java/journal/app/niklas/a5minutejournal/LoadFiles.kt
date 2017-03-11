package journal.app.niklas.a5minutejournal

import android.app.Activity
import android.content.Context
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_tabs.*
import java.io.File
import java.io.FileInputStream

/**
 * Created by nwuensche on 19.02.17.
 */
object LoadFiles {

    fun getTextFromFile(context: Context, date: String): String {
        var buffer = ByteArray(0)
        val dateFileName: String = FileName.convertDateToFileName(date)

        try {
            val file = File(context.filesDir, dateFileName)
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

/*
    fun removeFile(activity: Activity, date: String) {
        val file = File(activity.baseContext.filesDir, FileName.convertDateToFileName(date))
        file.delete()
    }
*/

    fun loadTodaysTextFromFileToView(context: Context) {
        if(!doesTodaysExist(context)) {
            val emptyContent: String = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            SaveFiles.saveFile(context, Today.getDate(), emptyContent)
            return
        }

        val lines = getTextFromFile(context, Today.getDate()).split("\n")

        fillTextViews(lines.iterator(), context)
    }

    fun loadSomeDayTextFromFileToView(context: Context, date: String) {
        if(!doesDayExist(context, date)) {
            return
        }

        val lines = getTextFromFile(context, date).split("\n")

        fillTextViews(lines.iterator(), context)
    }

    private fun doesTodaysExist(context: Context): Boolean {
        return context.filesDir
                .listFiles()
                .map { it.name }
                .any { it.equals(Today.getTodayFileName()) }
    }

    private fun doesDayExist(context: Context, date: String): Boolean {
        return getAllDatesDisplayName(context).contains(date)
    }


    private fun fillTextViews(lines: Iterator<String>, context: Context) {
        val activity = context as Activity
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

    fun getAllDatesDisplayName(context: Context): List<String> {
        return context.filesDir
                .listFiles()
                .map { it.name }// 9 Feb -> 09 February
                .filter { Character.isDigit(it[0]) }
                .map { FileName.convertFileNameToRealDate(it) }
                .filter { FileName.filterRemoveTodayDate(it) }
                .map(::DateString)
                .sorted()
                .map { it.toString() }
                .plus("Today")
                .reversed()
    }

}