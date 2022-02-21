package booking.entity

class ServiceItem(
    val id: Long,
    val name: String,
    val description: String,
    val minBookingCount: Int,
    val maxBookingCount: Int,
    val paymentType: PaymentType,
    val stock: Int,
    val price: Int,
    val normalPrice: Int,
    val isAutoAccept: Boolean,
    val hasNecessaryOption: Boolean,
    val extraInfos: List<ExtraInfo>,

) {
}

data class ExtraInfo(
    val question: String,
    val answer: String,
)