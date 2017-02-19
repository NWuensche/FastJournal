package journal.app.niklas.a5minutejournal

import android.content.Context
import android.util.Log
import journal.app.niklas.a5minutejournal.LoadFiles.getAllDateFileNames
import java.io.FileOutputStream

/**
 * Created by nwuensche on 19.02.17.
 */
object SaveFiles {

    fun onSaveToday(activity: MainActivity) {
        val filename = Today.getToday().replace(" ", "_").plus(".txt")
        val string = "Hello world!"

        try {
            val outputStream: FileOutputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(string.toByteArray())
            outputStream.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

        getAllDateFileNames(activity)
                .forEach { Log.e("test", it) }

    }

}