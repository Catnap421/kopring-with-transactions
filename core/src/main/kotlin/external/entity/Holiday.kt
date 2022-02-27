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

enum class StatutoryHoliday {
    신정, 설날_이전, 설날_당일, 설날_이후, 삼일절, 석가탄신일, 어린이날, 현충일, 광복절, 개천절, 한글날, 추석_이전, 추석_당일, 추석_이후, 크리스마스
}
