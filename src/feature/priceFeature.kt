package feature

import services.HotelManager

class priceFeature(private val hotelService: HotelManager) {
    fun checkPrice(){
        println("enter your booking id")
        val bookingId = readLine()?.toInt()
        val checkBooking = hotelService.checkBookingId(bookingId)
        if(checkBooking) {
            println("do you have additional amenities ? y/n")
            val choice = readLine().toString()
            if (choice == "y") {
                do {
                    println("what is the amenities ?")
                    println("1. Extra Bed")
                    println("2. Wine")
                    println("3. Snack")
                    val choice2 = readLine()?.toInt()
                    println("how many ?")
                    val productValue = readLine()?.toInt()
                    hotelService.addAmenities(bookingId,choice2,productValue)
                    println("still have additional amenities ?")
                    println("1. yes")
                    println("2. no")
                    val choice3 = readLine()?.toInt()
                    if((choice3?:0) >= 2){
                        break
                    }
                }
                    while (choice2 != 4)
                    hotelService.priceTotal(bookingId)
            }
            else{
                hotelService.priceTotal(bookingId)
            }
        }
        else{
            println("booking Id not found")
        }
    }

}