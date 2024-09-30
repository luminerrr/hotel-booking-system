import data.Bookings
import data.Customers
import data.Rooms
import feature.priceFeature
import services.HotelManager
import services.PriceService
import services.RoomServices

fun main() {
    val bookings = Bookings().datas
    val rooms = Rooms().datas
    val customers = Customers().datas
    val hotelService = HotelManager(bookings, customers, rooms)
    val priceService = priceFeature(hotelService)

//    roomServices.addRoom()
//    roomServices.getRooms()

    while (true) {
        println("Welcome to the hotel, Please select one of this menu")
        println("1. Add Room")
        println("2. Book Room")
        println("3. Cancel Booking")
        println("4. Check Available Rooms")
        println("5. Price Calculations")
        println("6. EXIT")

        val selection = readLine()?.toIntOrNull()

        when(selection) {
            1 -> hotelService.addRoom()
            2 -> {hotelService.addBooking()}
            3 -> {hotelService.cancelBooking()}
            4 -> hotelService.getAvailableRooms()
            5 -> priceService.checkPrice()
            6 -> {
                println("Exiting ...")
                return
            }
        }
    }
}
