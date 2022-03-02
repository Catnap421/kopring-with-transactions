package bookingV2.serviceItem

class ServiceItem(
    val id: Long,
    val name: String,
    val bizProfileId: Long,
    val serviceProviderIds: List<Long>,
    val stock: Int,
    val minBookingCount: Int,
    val maxBookingCount: Int,
) {

    fun findServiceItemSchedule(): ServiceItemSchedule {
        return ServiceItemSchedule.getServiceItemScheduleDummy()
    }

}
