package services

import data.Rooms
import model.Room
import java.time.LocalDate
import java.time.format.DateTimeParseException

class RoomServices {
    private val rooms = Rooms().datas
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

}