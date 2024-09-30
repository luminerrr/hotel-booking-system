package model

import java.time.LocalDate

class Booking(
    var id: Int,
    var room: Room,
    var customer: Customer,
    var checkInDate: LocalDate,
    var checkOutDate: LocalDate,
    var totalPrice: Double
) {
}