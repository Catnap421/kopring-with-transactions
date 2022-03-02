package bookingV2.serviceItem

import java.time.LocalDate
import java.time.LocalTime

class ServiceItemSlot(
    val bizProfileId: Long,
    val providerId: Long?,
    val serviceItemId: Long,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
){
    companion object {
        fun getSlotsByServiceDummies(): List<ServiceItemSlot> {
            return listOf()
        }

        fun getSlotsByProviderDummies(): List<ServiceItemSlot> {
            return listOf(

            )
        }
    }
}