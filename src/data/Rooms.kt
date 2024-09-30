package data

import constant.RoomType
import model.Room
import java.time.LocalDate

class Rooms {
    var datas = mutableListOf<Room>(
        Room(1, RoomType.Suite.toString(), 200.00, mutableMapOf<LocalDate, Boolean>(), listOf("Wi-Fi", "TV", "Bathub")),
        Room(2, RoomType.Suite.toString(), 250.00, mutableMapOf<LocalDate, Boolean>(), listOf("Wi-Fi", "TV", "Bathub")),
        Room(3, RoomType.Double.toString(), 300.00, mutableMapOf<LocalDate, Boolean>(), listOf("Wi-Fi", "TV", "Bathub")),
        Room(4, RoomType.Single.toString(), 350.00, mutableMapOf<LocalDate, Boolean>(), listOf("Wi-Fi", "TV", "Bathub")),
        Room(5, RoomType.Single.toString(), 2350.00, mutableMapOf<LocalDate, Boolean>(), listOf("Wi-Fi", "TV", "Bathub")),
    )
}