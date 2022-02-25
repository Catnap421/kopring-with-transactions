package booking.service

import Schedule.entity.Schedule
import booking.entity.BizItem
import external.entity.Holiday
import io.kotest.core.config.ExperimentalKotest
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.spyk
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

        val daily = scheduleCalculator.calculateDaily(startDate = startDate, endDate = endDate, bizItem = bizItem, holidays = Holiday.getHolidayDummies(), schedules = Schedule.getScheduleDummies())
        println(daily)
    }

    should("calculate hourly")

    should("calculate is business day") {
        scheduleCalculator.calculateIsBusinessDay(LocalDate.now(), Schedule.getScheduleDummies()) shouldBe true
    }

    should("calculate is holiday") {
        scheduleCalculator.calculateIsHoliday(LocalDate.now(), Holiday.getHolidayDummies()) shouldBe false
    }
})
