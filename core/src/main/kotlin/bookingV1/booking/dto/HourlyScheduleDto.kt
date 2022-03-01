package bookingV1.booking.dto

import java.time.LocalDateTime

class HourlyScheduleDto(
    val bizItemId: Long,
    val slotId: Long,
    val duration: Int,
    val stock: Int, // 하루 전체재고?
    val unitStock: Int,
    val unitStartDateTime: LocalDateTime,
    val unitBookingCount: Int,
)