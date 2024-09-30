package model

import java.time.LocalDate

class Room(
    var id: Int,
    var type: String,
    var ratePerNight: Double,
    var amenities: List<String>,
    val availability: MutableMap<LocalDate, Boolean> = mutableMapOf()
) {
    init {
        require(ratePerNight > 0) { "Rate per night must be positive" }
        require(type in listOf("Single", "Double", "Suite")) { "Invalid room type" }
    }

    fun isAvailable(checkIn: LocalDate, checkOut: LocalDate): Boolean {
        return generateSequence(checkIn) { date ->
            date.plusDays(1).takeIf { it <= checkOut }
        }.all { availability[it] ?: true }  // No more nullability issue
    }
}