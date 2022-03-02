package bookingV2.booking.service

import bookingV2.bizProfile.BizProfileSchedule
import bookingV2.provider.ProviderSchedule
import bookingV2.serviceItem.ServiceItemSchedule
import io.kotest.core.config.ExperimentalKotest
import io.kotest.core.spec.style.ShouldSpec
import io.mockk.spyk
import schedule.entity.rangeTo
import java.time.LocalDate
import java.time.LocalTime

@ExperimentalKotest
internal class TableCalculatorTest : ShouldSpec({

    val tableCalculator = spyk(
        TableCalculator()
    )

    should("calculate daily"){
        val bizProfileSchedule = BizProfileSchedule.getBizProfileScheduleDummy()
        val providerSchedule = ProviderSchedule.getProviderScheduleDummy()
        val serviceSchedule = ServiceItemSchedule.getServiceItemScheduleDummy()
        val startDate = LocalDate.parse("2022-02-01")
        val endDate = LocalDate.parse("2022-03-31")

        val dailyTables = tableCalculator.calculateDaily(startDate, endDate, bizProfileSchedule, providerSchedule, serviceSchedule)

        dailyTables.map {
            println("date: ${it.date}, day: ${it.date.dayOfWeek}, isBusinessDay: ${it.isBusinessDay}")
        }
    }

    should("calculate hourly"){
        val bizProfileSchedule = BizProfileSchedule.getBizProfileScheduleDummy()
        val providerSchedule = ProviderSchedule.getProviderScheduleDummy()
        val serviceSchedule = ServiceItemSchedule.getServiceItemScheduleDummy()
        val date = LocalDate.parse("2022-03-29")

        val hourlyTables = tableCalculator.calculateHourly(date, bizProfileSchedule, providerSchedule, serviceSchedule)

        hourlyTables.map {
            println("start time: ${it.startTime}, stock: ${it.stock}")
        }
    }
})
