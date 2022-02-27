package schedule.entity

import extension.DateIterator
import extension.DateProgression
import io.kotest.core.config.ExperimentalKotest
import io.kotest.core.spec.style.ShouldSpec
import java.time.LocalDate

@ExperimentalKotest
class BookingOperationTimeTest : ShouldSpec({

    should("is holiday and is business day") {
        val bookingOperationTime = BookingOperationTime.getBookingOperationTimeDummy()
        val startDate = LocalDate.parse("2022-02-01")
        val endDate = LocalDate.parse("2022-03-31")

        for (date in startDate..endDate) {
            println("date: $date, day: ${date.dayOfWeek}, isHoliday: ${bookingOperationTime.calculateIsHoliday(date)}, isOperationDay: ${bookingOperationTime.calculateIsOperationDay(date)}")
            println("isBusinessDay: ${bookingOperationTime.calculateIsBusinessDay(date)}")
        }
    }
})

class DateIterator(
    val startDate: LocalDate,
    val endDateInclusive: LocalDate,
    val stepDays: Long
) : Iterator<LocalDate> {
    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): LocalDate {

        val next = currentDate

        currentDate = currentDate.plusDays(stepDays)

        return next
    }
}

class DateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    val stepDays: Long = 1
) : Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)
}

operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)
