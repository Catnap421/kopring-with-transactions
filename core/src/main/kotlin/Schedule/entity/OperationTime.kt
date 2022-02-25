//package Schedule.entity
//
//import java.time.LocalDate
//import java.time.LocalTime
//
//class OperationTime(
//    val id: Long,
//    val businessAccountActor: Long,
//    val type: ScheduleType,
//    val startTime: LocalTime,
//    val endTime: LocalTime,
//    val description: String,
//    val isDayOff: Boolean = false, // 특정 날짜를 갑자기 쉬고 싶을 때 사용
//
//    val bizItemId: Long? = null, // 상품형
//    val duration: Int? = null,
//) {
//    var date: LocalDate? = null // register(등록형) 의 경우에 한해서만 date를 등록
//
//    constructor(
//        id: Long,
//        businessAccountActor: Long,
//        type: ScheduleType,
//        startTime: LocalTime,
//        endTime: LocalTime,
//        description: String,
//        isDayOff: Boolean,
//        date: LocalDate
//    ) : this(id, businessAccountActor, type, startTime, endTime, description, isDayOff) {
//        if (type !is ScheduleType.Custom)
//            throw Exception()
//
//        this.date = date
//    }
//
//    companion object {
//        fun getOperationTimeDummies(): List<OperationTime>{
//            return listOf(
//                OperationTime(
//                    id = 1L,
//                    businessAccountActor = 7236L,
//                    bizItemId = 1L,
//                    type = ScheduleType.Week(WeekSchedule.WEEKDAY),
//                    startTime = LocalTime.parse("10:00"),
//                    endTime = LocalTime.parse("22:00"),
//                    description = "",
//                ),
//                OperationTime(
//                    id = 2L,
//                    businessAccountActor = 7236L,
//                    bizItemId = 1L,
//                    type = ScheduleType.Week(WeekSchedule.WEEKEND),
//                    startTime = LocalTime.parse("10:00"),
//                    endTime = LocalTime.parse("17:00"),
//                    description = "",
//                ),
//            )
//        }
//    }
//
//}
//
//enum class WeekSchedule { WEEKDAY, WEEKEND, }
//enum class DaySchedule { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, }
//enum class EverySchedule { EVERYDAY, }
//enum class CustomSchedule { REGISTER, }
//enum class ProfileSchedule { PROFILE, }
//
//sealed class ScheduleType {
//    data class Week(val schedule: WeekSchedule) : ScheduleType()
//    data class Day(val schedule: DaySchedule) : ScheduleType()
//    data class Every(val schedule: EverySchedule) : ScheduleType()
//    data class Custom(val schedule: CustomSchedule) : ScheduleType()
//    data class Profile(val schedule: ProfileSchedule) : ScheduleType() // target으로 빼기
//}
//
//
//enum class ScheduleType(
//    val childTypes: List<DetailScheduleType>
//) {
//    EVERYDAY(emptyList()),
//    WEEKDAY_WEEKEND(listOf(DetailScheduleType.WEEKDAY, DetailScheduleType.WEEKEND)),
//    DAY_OF_THE_WEEK(listOf(DetailScheduleType.MONDAY, DetailScheduleType.TUESDAY, DetailScheduleType.WEDNESDAY, DetailScheduleType.THURSDAY, DetailScheduleType.FRIDAY, DetailScheduleType.SATURDAY, DetailScheduleType.SUNDAY)),
//    REGISTER(emptyList()),
//    BIZ_ITEM(emptyList()),
//    ;
//
//    enum class DetailScheduleType {
//        WEEKDAY, WEEKEND, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY,
//    }
//
//    fun getScheduleKorList(scheduleType: ScheduleType): List<String> {
//        return when (scheduleType) {
//            EVERYDAY -> listOf("매일")
//            WEEKDAY_WEEKEND -> listOf("주중", "주말")
//            DAY_OF_THE_WEEK -> listOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")
//            REGISTER -> listOf("등록형")
//            else -> emptyList()
//        }
//    }
//
//
//}
//
//
//
//
///**
// * bizHour: [,…]
//0: {type: "매일", startTime: "11:00", endTime: "22:00", description: "주문 마감 21:20, 연중무휴", isDayOff: false}
//bizhourInfo: "매일 11:00~22:00 주문 마감 21:20, 연중무휴"
// */
//
///**
// * bizHour: [,…]
//0: {type: "토요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
//1: {type: "금요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
//2: {type: "목요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
//3: {type: "수요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
//4: {type: "화요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
//5: {type: "일요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
//bizhourInfo: "토요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 금요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 목요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 수요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 화요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 일요일 11:00~22:00 라스트오더 오후 9시까지입니다."
// */
//
///**
// * type startTime ~ endTime description |
// */
