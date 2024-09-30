package services

import data.Rooms
import model.Room

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

    fun displayRooms() {
        if (rooms.isEmpty()) {
            println("No rooms available.")
        } else {
            println("List of Rooms:")
            rooms.forEach { room ->
                println("ID: ${room.id}, Type: ${room.type}, Rate: ${room.ratePerNight}, Amenities: ${room.amenities.joinToString()}")
            }
        }
    }

}