package booking.service

import booking.entity.BizItem
import external.entity.Holiday
import io.kotest.core.config.ExperimentalKotest
import io.kotest.core.spec.style.ShouldSpec
import io.mockk.spyk
import openingHours.entity.OpeningHour

@ExperimentalKotest
class SlotCalculatorTest : ShouldSpec({

    val mockSlotCalculator = spyk(SlotCalculator())

    should("hihi") {
        val holidays = Holiday.getHolidayDummies()
        val bizItem = BizItem.getBizItemDummy()
        val openingHours = OpeningHour.getOpeningHourDummies()

        mockSlotCalculator.calculateSlots(bizItem, holidays, openingHours)
    }
})
