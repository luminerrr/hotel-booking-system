package services

import data.Bookings
import data.Customers
import data.Rooms
import model.Booking
import model.Customer
import model.Room
import utils.DateUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class BookingService(
    var bookings: MutableList<Booking>,
    var customers: MutableList<Customer>,
    var rooms: MutableList<Room>
) {
    var dateUtils = DateUtils()


    fun addBooking(
        customerId: Int?, roomId: Int?,
        checkInDate: String?, checkOutDate: String?
    ) {
        if (customerId == null || roomId == null || checkInDate == null || checkOutDate == null) {
            println("Please use valid input!")
            return
        }
//        Check Customer
        val selectedUser = customers.find { it.id == customerId }
        if (selectedUser == null) {
            println("Sorry the customer you choose doesn't exist.")
            return
        }
//        Check room
        val selectedRoom = rooms.find { it.id == roomId }
        if (selectedRoom == null) {
            println("Sorry the room you choose doesn't exist")
            return
        }
//        Check date
        if (!dateUtils.isDateFormat(checkInDate) || !dateUtils.isDateFormat(checkOutDate)) {
            println("Please input right date format")
            return
        }
//        Convert date
        val checkInLocal = dateUtils.fromStringToLocalDate(checkInDate)
        val checkOutLocal = dateUtils.fromStringToLocalDate(checkOutDate)
        if (checkOutLocal.isAfter(checkInLocal)) {
            println("Check out date must be later than check in date!")
            return
        }
//        checks availability
        for (isAvailable in selectedRoom.availability.values) {
            if (!isAvailable) {
                println("Sorry, room ${selectedRoom.id} isn't available")
                return
            }
        }
        val totalBookingDays = ChronoUnit.DAYS.between(checkInLocal, checkOutLocal)
        val newBookId = if (bookings.size != 0) bookings.last().id + 1 else 1
//        Add new entry


//        Change Room availability
        for (roomIndex in rooms.indices) {
            if (rooms[roomIndex].id == roomId) {
                for (date in dateUtils.getDatesInRange(checkInLocal, checkOutLocal)) {
                    rooms[roomIndex].availability[date] = false
                }
                val newBooking = Booking(
                    newBookId,
                    rooms[roomIndex],
                    selectedUser,
                    checkInLocal,
                    checkOutLocal,
                    totalBookingDays * selectedRoom.ratePerNight
                )
                bookings.add(newBooking)
                println("Successfully book room $roomId from $checkInDate to $checkOutDate")
                return;
            }
        }
    }

    fun cancelBooking(bookingId: Int?) {
        if(bookingId == null) {
            println("Please input the right booking id format")
            return;
        }
        val selectedBooking = bookings.find { it.id == bookingId }
        if (selectedBooking == null) {
            println("No available booking for id $bookingId")
            return
        }
//        Remove booking
        bookings.remove(selectedBooking)
//        Make all dates available for the affected room
        for (roomIndex in rooms.indices) {
            if(rooms[roomIndex].id == selectedBooking.room.id) {
                for (availabilities in rooms[roomIndex].availability) {
                    rooms[roomIndex].availability[availabilities.key] = true
                }
                println("Successfully remove booking")
                return
            }
        }
    }
}