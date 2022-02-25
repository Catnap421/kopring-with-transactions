package booking.service

import Schedule.entity.*
import booking.dto.DailyScheduleDto
import booking.entity.BizItem
import extension.rangeTo
import external.entity.Holiday
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId


@Service
class ScheduleCalculator(
    val slotCommandService: SlotCommandService,
) {
//
//    fun calculateHourly(startDate: LocalDate, bizItem: BizItem, holidays: List<Holiday>, operationTimes: List<OperationTime>) {
//        // Input : 특정 날짜, 상품
//        // 원래라면 내부에서, 오프닝 시간을 읽어와야 함
//        val slots = slotCommandService.getSlotsByBizItemAndStartDate(1L, startDate)
//
//        val duration = operationTimes.firstOrNull()?.duration ?: throw Exception("일정을 등록해주세요")
//
//        operationTimes.map {
//            when (it.type) {
//                ScheduleType.WEEKEND -> TODO()
//                ScheduleType.WEEKDAY -> TODO()
//                else -> TODO()
//            }
//        }
//
//        println("bizItem : $bizItem, holidays : $holidays, openingHours: $operationTimes")
//    }

    fun calculateDaily(startDate: LocalDate, endDate: LocalDate, bizItem: BizItem, holidays: List<Holiday>, schedules: List<Schedule>) {
        // schedule 은 biz-item의 schedule
        val result = mutableListOf<DailyScheduleDto>()

        for (date in startDate..endDate){
            result.add(DailyScheduleDto(
                date = date,
                isHoliday = calculateIsHoliday(date, holidays),
                isBusinessDay = calculateIsBusinessDay(date, schedules),
                stock = bizItem.stock,
                bookingCount = 0, // 해당 날짜에 해당하는 슬롯 가져와서 sum(slot.getBookingCount),
                minBookingCount = bizItem.minBookingCount,
                maxBookingCount = bizItem.maxBookingCount,
            ))
        }


    }

    fun calculateIsHoliday(date: LocalDate, holidays: List<Holiday>): Boolean {
        return holidays.any { it.date == date }
    }

    fun calculateIsBusinessDay(date: LocalDate, schedules: List<Schedule> /* getProductTargetSchedule() */): Boolean {
        return schedules
            .filter { filterDateSchedule(date, it) }
            .all { it.isDayOff }
    }

    private fun filterDateSchedule(date: LocalDate, schedule: Schedule): Boolean{
        val day: DayOfWeek = date.dayOfWeek

        return when(val template = schedule.template) {
            is ScheduleTemplate.Every -> true
            is ScheduleTemplate.Week -> if(template.schedule == WeekTemplate.WEEKEND) calculateIsWeekend(day) else !calculateIsWeekend(day)
            is ScheduleTemplate.Day -> template.schedule.name == day.name
            is ScheduleTemplate.Custom -> schedule.date == date
        }
    }

    private fun calculateIsWeekend(day: DayOfWeek): Boolean {
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY
    }

}
