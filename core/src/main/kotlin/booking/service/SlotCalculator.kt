package booking.service

import booking.entity.BizItem
import external.entity.Holiday
import openingHours.entity.OpeningHour
import org.springframework.stereotype.Service

@Service
class SlotCalculator(

){
    fun calculateSlots(bizItem: BizItem, holidays: List<Holiday>, openingHours: List<OpeningHour>){


        println("bizItem : ${bizItem}, holidays : ${holidays}, openingHours: ${openingHours}")

    }

}