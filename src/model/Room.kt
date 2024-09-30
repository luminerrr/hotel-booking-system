package model

import java.time.LocalDate

class Room(
    var id: Int,
    var type: String,
    var ratePerNight: Double,
    var availability: Map<LocalDate, Boolean>?,
    var amenities: List<String>
) {
    init {
        require(ratePerNight > 0) { "Rate per night must be positive" }
    }

    fun isAvailable(checkIn: LocalDate, checkOut: LocalDate): Boolean {
        return generateSequence(checkIn) { date ->
            date.plusDays(1).takeIf { it <= checkOut }
        }.all { availability[it] ?: true }
    }
}