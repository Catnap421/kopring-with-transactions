package booking.entity

import java.time.LocalDate
import java.time.LocalDateTime

class Slot(
    val id: Long,
    val businessAccountId: Long,
    val bizItemId: Long,
    val stock: Int,
    val bookingCount: Int,
    val startTime: LocalDateTime,
    val duration: Int = 1800, // 30분을 기본 단위로..?
) {

    companion object {
        fun getSlotsByBizItemAndStartDate(bizItemId: Long, startDate: LocalDate): List<Slot> {
            println("query getSlotsByDate with $startDate")
            return listOf(
                Slot(
                    id = 1L,
                    businessAccountId = 1L,
                    bizItemId = bizItemId,
                    stock = 6,
                    bookingCount = 1,
                    startTime = LocalDateTime.parse("2022-03-04T10:30")
                )
            )
        }

        fun getSlotsByBusinessAccountAndStartDate():List<Slot>{
            // 슬롯을 여러 상품이 공유할 수 있으면 좋겠다.
            return listOf()
        }
    }
}
