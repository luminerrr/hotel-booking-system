package model

import java.time.LocalDate

class Room(
    var id: Int,
    var type: String,
    var ratePerNight: Double,
    var availability: MutableMap<LocalDate, Boolean>,
    var amenities: List<String>
) {

}