package external.entity

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Holiday(
    val dateKind: Long, // 국경일, 기념일, 24절기, 잡절
    val dateName: String,
    val date: LocalDate,
) {
    companion object {
        fun getHolidayDummies(): List<Holiday> {
            return listOf(
                Holiday(1L, "삼일절", LocalDate.parse("20220301", DateTimeFormatter.ofPattern("yyyyMMdd"))),
                Holiday(1L, "대통령선거일", LocalDate.parse("20220309", DateTimeFormatter.ofPattern("yyyyMMdd")))
            )
        }
    }
}
