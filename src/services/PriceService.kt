package services

import data.Bookings
import data.Customers
import data.Rooms
import data.SpecialAmenitiesesData
import java.time.temporal.ChronoUnit


class PriceService {
    private val customers = Customers()
    private val rooms = Rooms()
    private val specialAmenities = SpecialAmenitiesesData().datas
    private val bookings = Bookings(customers, rooms).datas

    fun checkBookingId(bookingId:Int?): Boolean{
        val booking = bookings.find{it.id == bookingId}
        if(booking != null){
            return true
        }
        return  false
    }
    fun priceTotal(bookingId:Int?){
        val booking = bookings.find{it.id == bookingId}
        if(booking != null){
            println(booking.id)
            println(booking.room.ratePerNight)
            val totalDate = ChronoUnit.DAYS.between(booking.checkInDate,booking.checkOutDate)
            println(totalDate)
            booking.totalPrice =(booking.totalPrice ?: 0.0) + booking.room.ratePerNight * totalDate
            if(totalDate >7){
                booking.totalPrice = booking.totalPrice!! *0.9
            }
            println(booking.totalPrice)
            return;
        }
        else{
            println("booking id $bookingId not found")
        }
    }

    fun addAmenities (bookingId: Int?,productId: Int?, productValue: Int?){
        val booking = bookings.find{it.id == bookingId}
        if(booking != null){
            val amenities = specialAmenities.find{it.id == productId}
            if(amenities != null){
                booking.totalPrice = (booking.totalPrice ?: 0.0) + (amenities.price * (productValue?:0))
                return
            }
        }
        else{
            println("booking id $bookingId not found")
        }

    }

}