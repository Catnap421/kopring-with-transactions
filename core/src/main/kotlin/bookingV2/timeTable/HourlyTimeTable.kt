package bookingV2.timeTable

import java.time.LocalTime

class HourlyTimeTable(
    val startTime: LocalTime,
    val unitMinutes: Long,
    val stock: Int,
) {
}