package bookingV1.schedule.entity

import java.time.DayOfWeek
import java.time.LocalDate

class ProfileOperationTime(
    val id: Long,
    val registrant: Long, // business account?? 등록자..?

    val targetType: TargetType, // 이거는 필요없을 지도?
    val targetId: Long,

//    val recursiveSchedules: List<Schedule>,
//    val registerSchedules: List<Schedule>,

    val operationDate: OperationDate,
    val baseOperationTimes: List<ScheduleV1>, // 기본 운영 시간
    val temporaryOperationTimes: List<ScheduleV1>, // 임시 운영 시간

    // todo 휴무일에 대한 엔티티 설계가 제대로 필요함
    val holidays: List<LocalDate>, // 법정 공휴일 중 쉬는 날 + 휴무일
    val regularHolidays: Set<DayOfWeek>, // 정기 휴무일(주로 요일)

    val unitMinutes: Long = 30,
    val bookablePeriod: BookablePeriod,
    /**
     * Todo
     * 법정공휴일 영업시간
     */
) {
}