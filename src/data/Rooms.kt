package data

import constant.RoomType
import model.Room
import java.time.LocalDate

class Rooms {
    var datas = mutableListOf(
        Room(1, RoomType.Suite.toString(), 200.00, listOf("Wi-Fi", "TV", "Bathtub"), mutableMapOf(
            LocalDate.parse("2024-10-01") to false,
            LocalDate.parse("2024-10-02") to false,
            LocalDate.parse("2024-10-03") to true,
            LocalDate.parse("2024-10-04") to false,
            LocalDate.parse("2024-10-05") to false,
            LocalDate.parse("2024-10-06") to true,
            LocalDate.parse("2024-10-07") to true,
            LocalDate.parse("2024-10-08") to true,
            LocalDate.parse("2024-10-09") to false
        )),
        Room(2, RoomType.Suite.toString(), 250.00, listOf("Wi-Fi", "TV", "Bathtub"), mutableMapOf(
            LocalDate.parse("2024-10-01") to false,  // Not available
            LocalDate.parse("2024-10-02") to false,
            LocalDate.parse("2024-10-03") to false,
            LocalDate.parse("2024-10-04") to false,
            LocalDate.parse("2024-10-05") to false,
            LocalDate.parse("2024-10-06") to false,
            LocalDate.parse("2024-10-07") to false,
            LocalDate.parse("2024-10-08") to false,
            LocalDate.parse("2024-10-09") to false
        )),
        Room(3, RoomType.Double.toString(), 300.00, listOf("Wi-Fi", "TV", "Bathtub"), mutableMapOf(
            LocalDate.parse("2024-10-01") to false,
            LocalDate.parse("2024-10-02") to true,
            LocalDate.parse("2024-10-03") to false,
            LocalDate.parse("2024-10-04") to true,
            LocalDate.parse("2024-10-05") to true,
            LocalDate.parse("2024-10-06") to true,
            LocalDate.parse("2024-10-07") to false,
            LocalDate.parse("2024-10-08") to false,
            LocalDate.parse("2024-10-09") to true
        )),
        Room(4, RoomType.Single.toString(), 350.00, listOf("Wi-Fi", "TV", "Bathtub"), mutableMapOf(
            LocalDate.parse("2024-10-01") to true,
            LocalDate.parse("2024-10-02") to false,
            LocalDate.parse("2024-10-03") to true,
            LocalDate.parse("2024-10-04") to true,
            LocalDate.parse("2024-10-05") to false,
            LocalDate.parse("2024-10-06") to false,
            LocalDate.parse("2024-10-07") to true,
            LocalDate.parse("2024-10-08") to false,
            LocalDate.parse("2024-10-09") to true
        )),
        Room(5, RoomType.Single.toString(), 235.00, listOf("Wi-Fi", "TV", "Bathtub"), mutableMapOf(
            LocalDate.parse("2024-10-01") to false,  // Not available
            LocalDate.parse("2024-10-02") to false,
            LocalDate.parse("2024-10-03") to false,
            LocalDate.parse("2024-10-04") to true,
            LocalDate.parse("2024-10-05") to true,
            LocalDate.parse("2024-10-06") to true,
            LocalDate.parse("2024-10-07") to false,
            LocalDate.parse("2024-10-08") to false,
            LocalDate.parse("2024-10-09") to true
        )),
        Room(6, RoomType.Double.toString(), 300.00, listOf("Wi-Fi", "AC", "Bathtub"), mutableMapOf(
            LocalDate.parse("2024-10-10") to false,
            LocalDate.parse("2024-10-11") to false,
            LocalDate.parse("2024-10-12") to false,
            LocalDate.parse("2024-10-13") to true,
            LocalDate.parse("2024-10-14") to true,
            LocalDate.parse("2024-10-15") to true,
            LocalDate.parse("2024-10-16") to false
        ))
    )
}


