package schedule.entity

import java.time.LocalDate
import java.time.LocalTime

class ScheduleTemplate(
    val templateType: TemplateType,
    val templateName: TemplateName,
) {
    init {
        val isRightMatched = when (templateType) {
            TemplateType.DAY -> templateName in listOf(
                TemplateName.MONDAY, TemplateName.TUESDAY, TemplateName.WEDNESDAY, TemplateName.THURSDAY, TemplateName.FRIDAY, TemplateName.SATURDAY, TemplateName.SUNDAY
            )
            TemplateType.WEEK -> templateName in listOf(
                TemplateName.WEEKDAY, TemplateName.WEEKEND
            )
            TemplateType.EVERY -> templateName in listOf(
                TemplateName.EVERYDAY
            )
            TemplateType.CUSTOM -> templateName in listOf(
                TemplateName.CUSTOM
            )
        }

        if (!isRightMatched) throw Exception("템플릿 타입과 템플릿이 잘못 매치되었습니다.")
    }
}

enum class TemplateType {
    DAY,
    WEEK,
    EVERY,
    CUSTOM
}

enum class TemplateName {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY,
    WEEKDAY, WEEKEND,
    EVERYDAY,
    CUSTOM
}

data class StartEndTime(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val stock: Int,
)

class Schedule(
    val template: ScheduleTemplate,
    val startEndTimes: List<StartEndTime>,
    val description: String? = null,
    val stock: Int,
) {
    var date: LocalDate? = null

    constructor(
        template: ScheduleTemplate,
        startEndTimes: List<StartEndTime>,
        description: String? = null,
        date: LocalDate,
        stock: Int,
    ) : this(template = template, startEndTimes = startEndTimes, description = description, stock = stock) {
        if (template.templateType != TemplateType.CUSTOM)
            throw Exception()

        this.date = date
    }

    companion object {
        fun getScheduleDummies(): List<Schedule> {
            return listOf(
                Schedule(
                    template = ScheduleTemplate(
                        templateType = TemplateType.DAY,
                        templateName = TemplateName.MONDAY,
                    ),
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                            stock = 10,
                        )
                    ),
                    description = "",
                    stock = 10,
                ),
                Schedule(
                    template = ScheduleTemplate(
                        templateType = TemplateType.DAY,
                        templateName = TemplateName.TUESDAY
                    ),
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                            stock = 10,
                        )
                    ),
                    description = "",
                    stock = 10,
                ),
                Schedule(
                    template = ScheduleTemplate(
                        templateType = TemplateType.DAY,
                        templateName = TemplateName.WEDNESDAY
                    ),
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                            stock = 10,
                        )
                    ),
                    description = "",
                    stock = 10
                ),
                Schedule(
                    template = ScheduleTemplate(
                        templateType = TemplateType.DAY,
                        templateName = TemplateName.FRIDAY
                    ),
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("22:00"),
                            stock = 10,
                        )
                    ),
                    description = "",
                    stock = 10,
                ),
                Schedule(
                    template = ScheduleTemplate(
                        templateType = TemplateType.DAY,
                        templateName = TemplateName.SATURDAY
                    ),
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
                            stock = 10,
                        )
                    ),
                    description = "",
                    stock = 10,
                ),
                Schedule(
                    template = ScheduleTemplate(
                        templateType = TemplateType.DAY,
                        templateName = TemplateName.SUNDAY
                    ),
                    startEndTimes = listOf(
                        StartEndTime(
                            startTime = LocalTime.parse("10:00"),
                            endTime = LocalTime.parse("17:00"),
                            stock = 10,
                        )
                    ),
                    description = "",
                    stock = 10,
                ),
            )
        }
    }
}

/**
 * bizHour: [,…]
0: {type: "매일", startTime: "11:00", endTime: "22:00", description: "주문 마감 21:20, 연중무휴", isDayOff: false}
bizhourInfo: "매일 11:00~22:00 주문 마감 21:20, 연중무휴"
 */

/**
 * bizHour: [,…]
0: {type: "토요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
1: {type: "금요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
2: {type: "목요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
3: {type: "수요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
4: {type: "화요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
5: {type: "일요일", startTime: "11:00", endTime: "22:00", description: "라스트오더 오후 9시까지입니다.", isDayOff: false}
bizhourInfo: "토요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 금요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 목요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 수요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 화요일 11:00~22:00 라스트오더 오후 9시까지입니다. | 일요일 11:00~22:00 라스트오더 오후 9시까지입니다."
 */

/**
 * type startTime ~ endTime description |
 */
