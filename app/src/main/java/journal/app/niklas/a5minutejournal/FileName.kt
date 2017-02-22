package journal.app.niklas.a5minutejournal

/**
 * Created by nwuensche on 22.02.17.
 */
object FileName {

    var date: String = ""

    // Can be "TODAY"
    fun getCurrFileDateOrToday(): String {
        if(date.equals("")) {
            return "TODAY"
        }

        return date
    }

    // Can't be "TODAY"
    fun getRealDate(): String {
        if(date.equals("") || date.toUpperCase().equals("TODAY")) {
            return Today.getDate()
        }

        return date
    }

    fun setCurrentFileDate(date: String) {
        this.date = date
    }

    fun convertDateToFileName(date: String): String {
        if(date.toUpperCase().equals("TODAY")) {
            return Today.getTodayFileName()
        }

        return date.replace(" ", "_").plus(".txt")
    }

    fun convertFileNameToDate(fileName: String): String {
        if(fileName.equals(Today.getTodayFileName())) {
            return "Today"
        }

        return fileName.replace("_", " ").substring(0, fileName.length - 4) // remove .txt
    }

}