package journal.app.niklas.a5minutejournal

/**
 * Created by nwuensche on 22.02.17.
 */
object FileName {

    private var date: String = ""

    /**
     * @return Can be "TODAY"
     */
    fun getCurrFileDateOrToday(): String {
        if(date.equals("")) {
            return "TODAY"
        }

        return date
    }

    /**
     * @return Can't be "TODAY"
     */
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

    fun convertFileNameToRealDate(fileName: String) : String {
        return fileName.substring(0, fileName.length-4).replace("_", " ")
    }

    fun filterRemoveTodayDate(fileName: String): Boolean {
        if(fileName.equals(Today.getDate())) {
            return false
        }

        return true
    }

}