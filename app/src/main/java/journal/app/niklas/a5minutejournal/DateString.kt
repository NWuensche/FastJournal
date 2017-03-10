package journal.app.niklas.a5minutejournal

/**
 * Created by nwuensche on 22.02.17.
 */
class DateString(val date: String) : Comparable<DateString> {

    override fun compareTo(other: DateString): Int {
        val splittedO1 = date.split(" ")
        val splittedO2 = other.toString().split(" ")

        if(splittedO1[2].compareTo(splittedO2[2]) != 0) {
            return splittedO1[2].compareTo(splittedO2[2])
        }

        if(numberMonth().compareTo(other.numberMonth()) != 0) {
            return numberMonth().compareTo(other.numberMonth())
        }

        return splittedO1[0].compareTo(splittedO2[0])

    }

    fun numberMonth(): Int {
        when(date.split(" ")[1].toUpperCase()) {
            "JANUARY" -> return 1
            "FEBRUARY" -> return 2
            "MARCH" -> return 3
            "APRIL" -> return 4
            "MAY" -> return 5
            "JUNE" -> return 6
            "JULY" -> return 7
            "AUGUST" -> return 8
            "SEPTEMBER" -> return 9
            "OCTOBER" -> return 10
            "NOVEMBER" -> return 11
            "DECEMBER" -> return 12
            else -> return 0
        }
    }

    override fun toString(): String {
        return date
    }
}