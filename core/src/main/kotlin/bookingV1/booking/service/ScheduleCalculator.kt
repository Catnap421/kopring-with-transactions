package bookingV1.booking.service

import bookingV1.booking.dto.DailyScheduleDto
import bookingV1.booking.entity.BizItem
import bookingV1.extension.rangeTo
import bookingV1.schedule.entity.BookingOperationTime
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ScheduleCalculator(
    val slotCommandService: SlotCommandService,
) {

//    fun calculateHourly(date: LocalDate, bizItem: BizItem, holidays: List<Holiday>, schedules: List<Schedule>) {
//        // Input : 특정 날짜, 상품
//        // 원래라면 내부에서, 오프닝 시간을 읽어와야 함
//        val slots = slotCommandService.getSlotsByBizItemAndStartDate(1L, date)
//
//        val duration = schedules.firstOrNull()?.duration ?: throw Exception("일정을 등록해주세요")
//
//        val todaySchedule = schedules
//            .filter { checkIsDateSchedule(date, it) }
//            .sortedBy { it.createdAt } // CUSTOM이 최우선.. 어떻게 적용할까낭..?
//            .first()
//
//        for (time in todaySchedule.startTime..todaySchedule.endTime step 30) {
//            HourlyScheduleDto(
//                bizItemId = ,
//                slotId = ,
//                duration = ,
//                stock = 99999, // 하루 전체 재고?
//                unitStock = ,
//                unitStartDateTime = ,
//                unitBookingCount =,
//            )
//        }
//
//        println("bizItem : $bizItem, holidays : $holidays, openingHours: $schedules")
//    }

    fun calculateDaily(
        startDate: LocalDate,
        endDate: LocalDate,
        bizItem: BizItem,
        bookingOperationTime: BookingOperationTime
    ): List<DailyScheduleDto> {

        // operationTime은 bizItem의 OperationTime
        val result = mutableListOf<DailyScheduleDto>()

        for (date in startDate..endDate) {
            result.add(
                DailyScheduleDto(
                    date = date,
                    isBusinessDay = bookingOperationTime.calculateIsBusinessDay(date),
                    stock = bookingOperationTime.temporaryOperationTimes.firstOrNull { it.date == date }?.stock
                    ?: bookingOperationTime.baseOperationTimes.firstOrNull { it.template.templateName.name == date.dayOfWeek.name }?.stock
                    ?: 0,
                    bookingCount = 0, // 해당 날짜에 해당하는 슬롯 가져와서 sum(slot.getBookingCount), hmmm...
                    minBookingCount = bizItem.minBookingCount,
                    maxBookingCount = bizItem.maxBookingCount,
                )
            )
        }

        return result
    }

}
