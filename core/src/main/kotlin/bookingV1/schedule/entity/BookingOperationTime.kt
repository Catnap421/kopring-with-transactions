package bookingV1.schedule.entity

import bookingV1.extension.rangeTo
import java.time.DayOfWeek
import java.time.LocalDate

enum class TargetType {
    PROFILE, PRODUCT,
}

enum class OperationType {
    ALLWAYS, PERIOD
}

data class OperationDate(
    val type: OperationType,
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now().plusYears(50L),
) // todo: startDate와 endDate 설정을 어떻게 할까? null로 할까?

data class BookablePeriod(
    val from: Long,
    val to: Long
) {
    init {
        if (from > to) throw Exception("예약 가능한 기간 설정이 잘못됐습니당")
    }
}

class BookingOperationTime(
    val id: Long,
    val registrant: Long, // business account user?? 등록자..? 어쩌면 role이 필요할지도?

    val targetType: TargetType, // 이거는 필요없을 지도?
    val targetId: Long,

    val operationDate: OperationDate,
    val baseOperationTimes: List<ScheduleV1>, // 기본 운영 시간
    val temporaryOperationTimes: MutableList<ScheduleV1> = mutableListOf(), // 임시 운영 시간

    val statutoryHolidays: List<LocalDate>, // todo 법정휴무일에 대한 엔티티 설계가 제대로 필요함(ENUM with 날짜)
    val regularHolidays: Set<DayOfWeek>, // 정기 휴무일(주로 요일)
    val extraHolidays: MutableList<LocalDate> = mutableListOf(), // 매년, 매달, 한번

    val unitMinutes: Long = 30,
    val bookablePeriod: BookablePeriod,
    /**
     * todo
     * 예약 오픈일
     * 노출 시작일
     * 미노출 시작일
     */
) {

    fun calculateIsBusinessDay(date: LocalDate): Boolean {
        return (calculateIsOperationDate(date) && !calculateIsHoliday(date) && calculateIsBaseOperationDay(date)) || calculateIsTemporaryOperationDay(date)
    }

    internal fun calculateIsOperationDate(date: LocalDate): Boolean {
        return when (operationDate.type) {
            OperationType.ALLWAYS -> true
            else -> date in operationDate.startDate!!..operationDate.endDate!!
        }
    }

    internal fun calculateIsHoliday(date: LocalDate): Boolean {
        return date in statutoryHolidays || date.dayOfWeek in regularHolidays || date in extraHolidays
    }

    internal fun calculateIsBaseOperationDay(date: LocalDate): Boolean {
        return date.dayOfWeek.name in baseOperationTimes.map { it.template.templateName.name }.toList()
        // todo: every 나 week type의 경우면 무조건 true 임
    }

    internal fun calculateIsTemporaryOperationDay(date: LocalDate): Boolean {
        return date in temporaryOperationTimes.map { it.date }.toList()
    }

    fun calculateDailyBusiness(date: LocalDate){ // 하루 단위 재고는 하루 단위의 예약이 아닌 경우엔 재고 컨트롤이 빡셀 필요가 없다.
        val isBusinessDay = calculateIsBusinessDay(date)
        val stock: Int = temporaryOperationTimes.firstOrNull { it.date == date }?.stock
            ?: baseOperationTimes.firstOrNull { it.template.templateName.name == date.dayOfWeek.name }?.stock
            ?: 0
    }

//    private fun checkIsDateSchedule(date: LocalDate, schedule: Schedule): Boolean {
//        val day: DayOfWeek = date.dayOfWeek
//
//        return when (val template = schedule.template) {
//            is ScheduleTemplate.Every -> true
//            is ScheduleTemplate.Week -> when (template.schedule) {
//                WeekTemplate.WEEKEND -> calculateIsWeekend(day)
//                else -> !calculateIsWeekend(day)
//            }
//            is ScheduleTemplate.Day -> template.schedule.name == day.name
//            is ScheduleTemplate.Custom -> schedule.date == date
//        }
//    }
//
//    private fun calculateIsWeekend(day: DayOfWeek): Boolean {
//        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY
//    }

    companion object {
        fun getBookingOperationTimeDummy(): BookingOperationTime {
            return BookingOperationTime(
                id = 1L,
                registrant = 7236L,
                targetType = TargetType.PRODUCT,
                targetId = 1L,
                operationDate = OperationDate(
                    type = OperationType.PERIOD,
                    startDate = LocalDate.parse("2022-02-20"),
                    endDate = LocalDate.parse("2022-03-28")
                ),
                baseOperationTimes = ScheduleV1.getScheduleDummies(),
                statutoryHolidays = listOf(LocalDate.parse("2022-02-02")),
                regularHolidays = setOf(DayOfWeek.FRIDAY),
                extraHolidays = mutableListOf(LocalDate.parse("2022-03-01")),
                unitMinutes = 30,
                bookablePeriod = BookablePeriod(0, 30),
            )
        }
    }
}
