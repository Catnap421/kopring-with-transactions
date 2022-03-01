package bookingV1.booking.service

import bookingV1.booking.entity.Slot
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SlotCommandService {

    fun getSlotsByBizItemAndStartDate(bizItemId: Long, startDate: LocalDate): List<Slot> {
        return Slot.getSlotsByBizItemAndStartDate(bizItemId, startDate)
    }
}
