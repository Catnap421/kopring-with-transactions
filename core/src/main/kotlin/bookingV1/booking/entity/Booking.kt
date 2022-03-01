package bookingV1.booking.entity

class Booking(
    val id: Long,
    val businessAccountActor : Long, // 실제론 Actor 로 받을 예정
    val slotId: Long,
    val userActor: Long, // 실제론 Actor 로 받을 예정
    val totalPrice: Int,
    val serviceItemId: Long,
    /**
     * 상태 관리 가능
     * 예약에 대한 종합정보
     * 인원 정보
     * 구매자 정보
     * 요청사항 답변
     * 주문 정보
     * slotId
     */
) {
}