package booking.repository

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

interface SlotRepository : CrudRepository<SlotEntity, Long>

@Entity
class SlotEntity(
    @Id
    val id: Long,
)