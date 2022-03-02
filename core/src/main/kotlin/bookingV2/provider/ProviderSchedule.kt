package bookingV2.provider

import bookingV2.entity.Schedule

class ProviderSchedule(
    val baseOperationTimes: List<Schedule>,
    val specialOperationTimes: List<Schedule>,
    val stock: Int,
){

    companion object {
        fun getProviderScheduleDummy(): ProviderSchedule{
            return ProviderSchedule(
                baseOperationTimes = Schedule.getProviderScheduleDummies(),
                specialOperationTimes = emptyList(),
                stock = 3,
            )
        }
    }
}