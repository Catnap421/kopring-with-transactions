package bookingV2.booking.service

import bookingV1.extension.rangeTo
import bookingV2.bizProfile.BizProfileSchedule
import bookingV2.entity.Schedule
import bookingV2.provider.ProviderSchedule
import bookingV2.serviceItem.ServiceItemSchedule
import bookingV2.timeTable.DailyTable
import bookingV2.timeTable.HourlyTimeTable
import org.springframework.stereotype.Service
import java.lang.Integer.min
import java.time.LocalDate
import java.time.LocalTime

@Service
class TableCalculator {
    fun calculateDaily(
        startDate: LocalDate,
        endDate: LocalDate,
        bizProfileSchedule: BizProfileSchedule,
        providerSchedule: ProviderSchedule,
        serviceSchedule: ServiceItemSchedule,
    ): List<DailyTable> {

        return startDate.rangeTo(endDate)
            .map {
                DailyTable(
                    date = it,
                    isBusinessDay = calculateIsBusinessDay(it, bizProfileSchedule, providerSchedule, serviceSchedule)
                )
            }.toList()
    }

    private fun calculateIsBusinessDay(date: LocalDate, bizProfileSchedule: BizProfileSchedule, providerSchedule: ProviderSchedule?, serviceSchedule: ServiceItemSchedule): Boolean {
        // 결국 오늘 일을 하냐 안하냐만 파악해주면 됨
        // 비즈프로필 스케줄 그리기
        // 제공자 스케줄 그리기
        // 서비스 스케줄 그리기
        val providerIsDayOff = if (providerSchedule == null) true
        else getDateSchedule(date, providerSchedule.baseOperationTimes, providerSchedule.specialOperationTimes).isDayOff

        return getDateSchedule(date, bizProfileSchedule.baseOperationTimes, bizProfileSchedule.specialOperationTimes).isDayOff &&
            providerIsDayOff &&
            getDateSchedule(date, serviceSchedule.baseOperationTimes, serviceSchedule.specialOperationTimes).isDayOff
    }

    private fun getDateSchedule(date: LocalDate, baseOperationTimes: List<Schedule>, specialOperationTimes: List<Schedule>): Schedule {
        return specialOperationTimes.firstOrNull { it.date == date }
            ?: baseOperationTimes.firstOrNull { it.dayOfWeek == date.dayOfWeek }
            ?: throw Exception("기본 운영시간은 모든 요일을 가져야 합니다.")
    }

    fun calculateHourly(
        date: LocalDate,
        bizProfileSchedule: BizProfileSchedule,
        providerSchedule: ProviderSchedule?,
        serviceSchedule: ServiceItemSchedule,
    ): List<HourlyTimeTable> {
        // 모든 시간 그리기
        var hourlyTimeTables = mutableListOf<HourlyTimeTable>()
        val unitMinutes = serviceSchedule.unitMinutes
        var time = LocalTime.MIN

        do {
            hourlyTimeTables.add(
                HourlyTimeTable(
                    startTime = time,
                    unitMinutes = unitMinutes,
                    stock = min(providerSchedule?.stock ?: 9999, serviceSchedule.stock)
                )
            )

            time = time.plusMinutes(unitMinutes)
        } while (time != LocalTime.MIN)

        // 스케줄 교집합 구하기
        val bizProfileDateSchedule = getDateSchedule(date, bizProfileSchedule.baseOperationTimes, bizProfileSchedule.specialOperationTimes)
        hourlyTimeTables = calculateIntersection(hourlyTimeTables, bizProfileDateSchedule, serviceSchedule.unitMinutes)

        if (providerSchedule != null) {
            val providerSchedule = getDateSchedule(date, providerSchedule.baseOperationTimes, providerSchedule.specialOperationTimes)
            hourlyTimeTables = calculateIntersection(hourlyTimeTables, providerSchedule, serviceSchedule.unitMinutes)
        }

        val serviceDateSchedule = getDateSchedule(date, serviceSchedule.baseOperationTimes, serviceSchedule.specialOperationTimes)
        hourlyTimeTables = calculateIntersection(hourlyTimeTables, serviceDateSchedule, serviceSchedule.unitMinutes)

        // 점유된 slot 제외하기

        return hourlyTimeTables
    }

    private fun calculateIntersection(hourlyTimeTables: List<HourlyTimeTable>, dateSchedule: Schedule, unitMinutes: Long): MutableList<HourlyTimeTable> {
        var tables = mutableListOf<HourlyTimeTable>()

        dateSchedule.startEndTimes.map {
            for (time in it.startTime..it.endTime.minusMinutes(unitMinutes) step unitMinutes) {
                hourlyTimeTables.firstOrNull{ table -> table.startTime == time }?.let { tables.add(it) }
            }
        }

        return tables.toMutableList()
    }
}
