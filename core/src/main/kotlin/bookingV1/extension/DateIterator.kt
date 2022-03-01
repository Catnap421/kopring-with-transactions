package bookingV1.extension

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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

class DateTimeIterator(
    val startDateTime: LocalDateTime,
    val endDateTimeInclusive: LocalDateTime,
    val stepMinutes: Long
) : Iterator<LocalDateTime> {
    private var currentDateTime = startDateTime

    override fun hasNext() = currentDateTime <= endDateTimeInclusive

    override fun next(): LocalDateTime {

        val next = currentDateTime

        currentDateTime = currentDateTime.plusMinutes(stepMinutes)

        return next
    }
}

class TimeIterator(
    val startTime: LocalTime,
    val endTimeInclusive: LocalTime,
    val stepMinutes: Long
) : Iterator<LocalTime> {
    private var currentTime = startTime

    override fun hasNext() = currentTime <= endTimeInclusive

    override fun next(): LocalTime {

        val next = currentTime

        currentTime = currentTime.plusMinutes(stepMinutes)

        return next
    }
}

