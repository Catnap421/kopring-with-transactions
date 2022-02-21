package booking.entity

class BookingOrderHistory(
    val bookingId: Long,
    val orderId: Long,
    val totalPrice: Int,
    val paymentType: PaymentType,
    val orderStatus: OrderStatus
) {
}


enum class OrderStatus {
    UNKNOWN, PRE_ORDER, PAYMENT_PENDING, PAYMENT_COMPLETE, CONFIRM_PURCHASE, CANCEL_ORDER
}