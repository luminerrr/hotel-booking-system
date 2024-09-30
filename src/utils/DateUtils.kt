package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateUtils {
    public fun fromStringToLocalDate(dateString: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        return LocalDate.parse(dateString, formatter)
    }

    public fun isDateFormat(dateString: String): Boolean {
        val regex = Regex("^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(19|20)\\d{2}$")
        return regex.matches(dateString)
    }

    public fun getDatesInRange(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        return generateSequence(startDate) { it.plusDays(1) }.takeWhile { it <= endDate }.toList()
    }
}