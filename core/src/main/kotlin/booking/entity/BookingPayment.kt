package booking.entity

class BookingPayment(
    val id: Long,

    val provisionalOrder: Order,
    val actualOrder: Order,
    val refundInfo: String, // String?
) {
}

data class Order(
    val id: Long,
    val price: Int,
    val paymentStatus: PaymentStatus,
)

enum class PaymentStatus{

}