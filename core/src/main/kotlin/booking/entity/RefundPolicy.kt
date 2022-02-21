package booking.entity

class RefundPolicy(
    /**
     * 일별로 환불 퍼센트 설정 가능
     * 결제 직후 취소 가능시간 설정(1 ~ 12 시간까지 설정 가능. 결제 한 뒤 10시간 이내 취소는 전액 환불)
     * 결제 직후 취소 기준시간 설정(0 ~ 23 시까지 설정 가능. 23시라고 한다면, 당일에 오후 11시가 지나가면 전액 환불 불가능)
     * 취소 수수료
     */
) {
    fun refundFromConfirmedToCancelled(){
        TODO("유저가 취소하는 경우에만 환불메타 적용")
    }

    fun refundFromConfirmedToNoShow(){
        return refundFromCompletedToCancelled()
    }

    fun refundFromCompletedToCancelled(){
        TODO("사장님만 가능" +
                "환불 수수료를 사장님이 결정")
    }

}