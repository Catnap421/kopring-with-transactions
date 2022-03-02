package bookingV2.serviceItem

import bookingV2.entity.Schedule
import bookingV2.entity.ScheduleType
import java.time.LocalDate

class ServiceItemSchedule(
    val baseOperationTimes: List<Schedule>,
    val specialOperationTimes: List<Schedule>,
    val unitMinutes: Long,
    val duration: Long,
    val stock: Int,
) {
    companion object {
        fun getServiceItemScheduleDummy(): ServiceItemSchedule {
            return ServiceItemSchedule(
                baseOperationTimes = Schedule.getServiceItemScheduleDummies(),
                specialOperationTimes = listOf(
                    Schedule(
                        type = ScheduleType.SPECIAL,
                        startEndTimes = emptyList(),
                        date = LocalDate.parse("2022-03-31"),
                        isDayOff = true
                    )
                ),
                unitMinutes = 60,
                duration = 60,
                stock = 5,
            )
        }
    }
}
