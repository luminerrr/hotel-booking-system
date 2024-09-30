package data

import model.Booking
import java.time.LocalDate

class Bookings(private val customers: Customers, private val rooms: Rooms) {
    var datas = mutableListOf<Booking>(
        Booking(
            1,
            rooms.datas[0],
            customers.datas[0],
            LocalDate.of(2024, 10, 1),
            LocalDate.of(2024, 10, 5),null
        ),Booking(
            2,
            rooms.datas[1],
            customers.datas[1],
            LocalDate.of(2024, 10, 1),
            LocalDate.of(2024, 10, 10),null
        )
    )
}
