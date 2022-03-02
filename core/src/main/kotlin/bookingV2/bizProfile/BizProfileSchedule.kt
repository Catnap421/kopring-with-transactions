package bookingV2.bizProfile

import bookingV2.entity.Schedule
import bookingV2.entity.ScheduleType
import bookingV2.entity.StartEndTime
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class BizProfileSchedule(
    val baseOperationTimes: List<Schedule>,
    val specialOperationTimes: List<Schedule>,
){

    companion object {
        fun getBizProfileScheduleDummy(): BizProfileSchedule{
            return BizProfileSchedule(
                baseOperationTimes = Schedule.getBizProfileScheduleDummies(),
                specialOperationTimes = listOf(
                    Schedule(
                        type = ScheduleType.SPECIAL,
                        startEndTimes = listOf(
                            StartEndTime(
                                startTime = LocalTime.parse("10:00"),
                                endTime = LocalTime.parse("12:00"),
                            )
                        ),
                        date = LocalDate.parse("2022-03-04")
                    )
                )
            )
        }
    }
}