package booking.dto

import java.time.LocalDate
import java.time.LocalTime

class DailyScheduleDto(
    val date: LocalDate,
    val isHoliday: Boolean,
    val isBusinessDay: Boolean,
    val stock: Int, // 하루 재고는 어디에 설정하지..? 1. bizItem
    val bookingCount: Int,
    val minBookingCount: Int,
    val maxBookingCount: Int,
//    val startTime: LocalTime,
//    val endTime: LocalTime,
)
