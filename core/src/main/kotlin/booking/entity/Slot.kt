package booking.entity

import java.time.LocalDateTime

class Slot(
    val id: Long,
    val stock: Int,
    val bookingCount: Int,
    val startTime: LocalDateTime,
    val duration: Int = 1800, // 30분을 기본 단위로..?
) {
}