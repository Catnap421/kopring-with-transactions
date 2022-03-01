package booking.service

import bookingV1.booking.entity.BizItem
import bookingV1.booking.service.ScheduleCalculator
import bookingV1.booking.service.SlotCommandService
import bookingV1.external.entity.Holiday
import io.kotest.core.config.ExperimentalKotest
import io.kotest.core.spec.style.ShouldSpec
import io.mockk.spyk
import bookingV1.schedule.entity.BookingOperationTime
import java.time.LocalDate

@ExperimentalKotest
class ScheduleCalculatorTest : ShouldSpec({

    val scheduleCalculator = spyk(
        ScheduleCalculator(
            slotCommandService = SlotCommandService()
        )
    )

    should("calculate daily") {
        val holidays = Holiday.getHolidayDummies()
        val bizItem = BizItem.getBizItemDummy()
//        val schedules = OperationTime.getOperationTimeDummies() // 상품 ID를 기반으로 가져와야 함!
        val startDate = LocalDate.parse("2022-03-01")
        val endDate = LocalDate.parse("2022-03-31")

        val daily = scheduleCalculator.calculateDaily(startDate = startDate, endDate = endDate, bizItem = bizItem, bookingOperationTime = BookingOperationTime.getBookingOperationTimeDummy())
        println(daily)
    }

})
