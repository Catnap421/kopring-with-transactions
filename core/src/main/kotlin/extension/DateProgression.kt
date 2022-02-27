package extension

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    val stepDays: Long = 1
) : Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)
}

class DateTimeProgression(
    override val start: LocalDateTime,
    override val endInclusive: LocalDateTime,
    val stepMinutes: Long = 30
) : Iterable<LocalDateTime>, ClosedRange<LocalDateTime> {

    override fun iterator(): Iterator<LocalDateTime> =
        DateTimeIterator(start, endInclusive, stepMinutes)

    infix fun step(days: Long) = DateTimeIterator(start, endInclusive, days)
}

class TimeProgression(
    override val start: LocalTime,
    override val endInclusive: LocalTime,
    val stepMinutes: Long = 30
) : Iterable<LocalTime>, ClosedRange<LocalTime> {

    override fun iterator(): Iterator<LocalTime> =
        TimeIterator(start, endInclusive, stepMinutes)

    infix fun step(days: Long) = TimeIterator(start, endInclusive, days)
}

operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)

operator fun LocalDateTime.rangeTo(other: LocalDateTime) = DateTimeProgression(this, other)

operator fun LocalTime.rangeTo(other: LocalTime) = TimeProgression(this, other)