package Schedule.entity

import java.time.LocalDate
import java.time.LocalTime

class Schedule(
    val id: Long,
    val registrant: Long, // business account??
    val target: ScheduleTarget,
    val template: ScheduleTemplate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val description: String,
    val isDayOff: Boolean = false, // 특정 날짜를 갑자기 쉬고 싶을 때 사용

    val bizItemId: Long? = null, // 상품형
    val duration: Int? = null,
) {
    var date: LocalDate? = null

    constructor(
        id: Long,
        registrant: Long,
        target: ScheduleTarget,
        template: ScheduleTemplate,
        startTime: LocalTime,
        endTime: LocalTime,
        description: String,
        isDayOff: Boolean,
        bizItemId: Long? = null,
        duration: Int? = null,
        date: LocalDate
    ) : this(id, registrant, target, template, startTime, endTime, description, isDayOff, bizItemId, duration) {
        if (template !is ScheduleTemplate.Custom)
            throw Exception()

        this.date = date
    }

}

enum class ScheduleTarget {
    PROFILE, PRODUCT,
}

enum class WeekTemplate { WEEKDAY, WEEKEND, }
enum class DayTemplate { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, }
enum class EveryTemplate { EVERYDAY, }
enum class CustomTemplate { CUSTOM, }

sealed class ScheduleTemplate {
    data class Week(val schedule: WeekTemplate) : ScheduleTemplate()
    data class Day(val schedule: DayTemplate) : ScheduleTemplate()
    data class Every(val schedule: EveryTemplate) : ScheduleTemplate()
    data class Custom(val schedule: CustomTemplate) : ScheduleTemplate()
}
