package data

import constant.RoomType
import model.Room
import java.time.LocalDate

class Rooms {
    var datas = mutableListOf(
        Room(1, RoomType.Suite.toString(), 200.00, listOf("Wi-Fi", "TV", "Bathub"), mutableMapOf()),
        Room(2, RoomType.Suite.toString(), 250.00, listOf("Wi-Fi", "TV", "Bathub"), mutableMapOf(
            LocalDate.now() to true,
            LocalDate.now().plusDays(1) to false
        )),
        Room(3, RoomType.Double.toString(), 300.00, listOf("Wi-Fi", "TV", "Bathub"), mutableMapOf(
            LocalDate.now() to true,
            LocalDate.now().plusDays(2) to true
        )),
        Room(4, RoomType.Single.toString(), 350.00, listOf("Wi-Fi", "TV", "Bathub"), mutableMapOf()),
        Room(5, RoomType.Single.toString(), 235.00, listOf("Wi-Fi", "TV", "Bathub"), mutableMapOf(
            LocalDate.now() to false
        ))
    )
}
