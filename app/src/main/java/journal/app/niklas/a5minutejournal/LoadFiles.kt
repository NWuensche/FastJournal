package journal.app.niklas.a5minutejournal

import java.io.File
import java.io.FileInputStream

/**
 * Created by nwuensche on 19.02.17.
 */
object LoadFiles {

    fun getAllDateFileNames(activity: MainActivity): List<String> {
        return activity.filesDir
                .listFiles()
                .map { it.name }
                .filter { Character.isDigit(it[0]) } // 9 Feb -> 09 February
    }

    fun getTextFromFile(activity: MainActivity, fileName: String): String {
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

}