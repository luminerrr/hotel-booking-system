package feature

import services.PriceService

class priceFeature(private val priceService: PriceService) {
    fun checkPrice(){
        println("enter your booking id")
        val bookingId = readLine()?.toInt()
        val checkBooking = priceService.checkBookingId(bookingId)
        if(checkBooking) {
            println("do you have additional amenities ? y/n")
            val choice = readLine().toString()
            if (choice == "y") {
                do {
                    println("what is the ameties ?")
                    println("1. Extra Bed")
                    println("2. Wine")
                    println("3. Snack")
                    println("4. exit")
                    val choice2 = readLine()?.toInt()
                    if((choice2?:0) >= 3){
                        break
                    }
                    println("how many ?")
                    val productValue = readLine()?.toInt()
                    priceService.addAmenities(bookingId,choice2,productValue)
                }
                    while (choice2 != 4)
                    priceService.priceTotal(bookingId)
            }
            else{
                priceService.priceTotal(bookingId)
            }
        }
        else{
            println("booking Id not found")
        }
    }

}