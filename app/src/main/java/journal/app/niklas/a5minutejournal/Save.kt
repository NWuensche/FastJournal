package journal.app.niklas.a5minutejournal

import android.content.Context
import android.view.View
import java.io.FileOutputStream

/**
 * Created by nwuensche on 19.02.17.
 */
object Save {

    fun onSaveToday(v: View) {
        val filename = Today.getToday().replace(" ", "_").plus(".txt")
        val string = "Hello world!"

        try {
            val outputStream: FileOutputStream = v.context.openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(string.toByteArray())
            outputStream.close()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

}