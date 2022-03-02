package bookingV2.entity

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

enum class ScheduleType {
    BASE, SPECIAL // TODO 스케줄 연동 고민
}

data class StartEndTime(
    val startTime: LocalTime, // todo LocalDateTime
    val endTime: LocalTime, // todo LocalDateTime
//    val stock: Int,
)

class Schedule(
    val type: ScheduleType,
    val dayOfWeek: DayOfWeek,
    val startEndTimes: List<StartEndTime>,
    val description: String? = null,
//    val stock: Int,
    val isDayOff: Boolean = false,
) {
    var date: LocalDate? = null

    constructor(
        type: ScheduleType,
        startEndTimes: List<StartEndTime>,
        description: String? = null,
        date: LocalDate,
        isDayOff: Boolean = false,
    ) : this(
        type = type,
        dayOfWeek = date.dayOfWeek,
        startEndTimes = startEndTimes,
        description = description,
        isDayOff = isDayOff
//        stock = stock
    ) {
        if (type != ScheduleType.SPECIAL)
            throw Exception()

        this.date = date
    }

    companion object {
        fun getBizProfileScheduleDummies(): List<Schedule> {
            return listOf(
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.MONDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("15:00"),
//                            stock = 10,
                        ),
                        StartEndTime(
                            startTime = LocalTime.parse("17:00"),
                            endTime = LocalTime.parse("22:00"),
//                            stock = 5,
                        )
                    ),
                    description = "",
//                    stock = 10,
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.TUESDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
//                            stock = 10,
                        )
                    ),
                    description = "",
//                    stock = 10,
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.WEDNESDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
//                            stock = 10,
                        )
                    ),
                    description = "",
//                    stock = 10,
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.THURSDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
//                            stock = 10,
                        )
                    ),
                    description = "",
//                    stock = 10,
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.SATURDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
//                            stock = 10,
                        )
                    ),
                    description = "",
//                    stock = 10,
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.SUNDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
//                            stock = 10,
                        )
                    ),
                    description = "",
//                    stock = 10,
                ),
            )
        }

        fun getProviderScheduleDummies(): List<Schedule> {
            return listOf(
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.MONDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("15:00"),
                        ),
                        StartEndTime(
                            startTime = LocalTime.parse("17:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.TUESDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.WEDNESDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.THURSDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.SATURDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.SUNDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
                        )
                    ),
                    description = "",
                ),
            )
        }

        fun getServiceItemScheduleDummies(): List<Schedule> {
            return listOf(
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.MONDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("15:00"),
                        ),
                        StartEndTime(
                            startTime = LocalTime.parse("17:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.TUESDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.WEDNESDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.THURSDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.SATURDAY,
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
                        )
                    ),
                    description = "",
                ),
                Schedule(
                    type = ScheduleType.BASE,
                    dayOfWeek = DayOfWeek.SUNDAY,
                    startEndTimes = emptyList(),
                    description = "",
                    isDayOff = true,
                ),
            )
        }
    }
}