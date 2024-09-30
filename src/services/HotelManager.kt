package services

import data.Bookings
import data.Customers
import data.Rooms
import model.Booking
import model.Customer
import model.Room
import utils.DateUtils
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

class HotelManager(
    var bookings: MutableList<Booking> = Bookings().datas,
    var customers: MutableList<Customer> = Customers().datas,
    val rooms: MutableList<Room> = Rooms().datas
) {
//    var bookings: MutableList<Booking> = Bookings().datas
//    var customers: MutableList<Customer> = Customers().datas
//    private val rooms = Rooms().datas

    fun addRoom() {
        val id = rooms.last().id + 1

        println("Enter room type (Single, Double, Suite): ")
        val type = readLine() ?: return
        println("Enter rate per night: ")
        val ratePerNight = readLine()?.toDoubleOrNull() ?: return
        println("Enter amenities (comma-separated): ")
        val amenities = readLine()?.split(",")?.map { it.trim() } ?: listOf()

        rooms.add(Room(id, type, ratePerNight, amenities))
    }

    fun getRooms() {
        if (rooms.isEmpty()) {
            println("No rooms available.")
        } else {
            println("List of Rooms:")
            rooms.forEach { room ->
                println("ID: ${room.id}, Type: ${room.type}, Rate: ${room.ratePerNight}, Amenities: ${room.amenities.joinToString()}")
            }
        }
    }

    fun getAvailableRooms() {
        try {
            // Input check in out
            println("Enter check-in date (YYYY-MM-DD): ")
            val checkIn = LocalDate.parse(readLine())

            println("Enter check-out date (YYYY-MM-DD): ")
            val checkOut = LocalDate.parse(readLine())

            // Validasi biar tanggal ga sama / sebelum
            if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
                println("Check-out date must be after check-in date.")
                return
            }

            // List available rooms
            val availableRooms = rooms.filter { room ->
                room.isAvailable(checkIn, checkOut)
            }

            if (availableRooms.isEmpty()) {
                println("No rooms available for the selected dates.")
            } else {
                println("Available rooms:")
                availableRooms.forEach { room ->
                    println("Room ID: ${room.id}, Type: ${room.type}, Rate: ${room.ratePerNight}, Amenities: ${room.amenities.joinToString()}")
                }
            }

        } catch (e: DateTimeParseException) {
            println("Invalid date format. Please enter the date in YYYY-MM-DD format.")
        }
    }

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
        if (bookingId == null) {
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
            if (rooms[roomIndex].id == selectedBooking.room.id) {
                for (availabilities in rooms[roomIndex].availability) {
                    rooms[roomIndex].availability[availabilities.key] = true
                }
                println("Successfully remove booking")
                return
            }
        }
    }


}