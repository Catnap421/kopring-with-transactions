package booking.entity

class BizItem(
    val id: Long,
    val name: String,
    val description: String,
    val minBookingCount: Int,
    val maxBookingCount: Int,
    val paymentType: PaymentType,
    val stock: Int,
    val price: Int,
    val normalPrice: Int,
    val isAutoAccept: Boolean = false,
    val hasNecessaryOption: Boolean = false,
    val extraInfos: List<ExtraInfo> = emptyList(),
) {

    companion object {
        fun getBizItemDummy() : BizItem {
            return BizItem(
                id = 1L,
                name = "Allen",
                description = "설명",
                minBookingCount = 1,
                maxBookingCount = 2,
                paymentType = PaymentType.ONSITE_PAYMENT,
                stock = 6,
                price = 19900,
                normalPrice = 20000,
            )
        }
    }
}

data class ExtraInfo(
    val question: String,
    val answer: String,
)