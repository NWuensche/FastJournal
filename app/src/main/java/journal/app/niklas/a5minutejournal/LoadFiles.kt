package journal.app.niklas.a5minutejournal

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

}