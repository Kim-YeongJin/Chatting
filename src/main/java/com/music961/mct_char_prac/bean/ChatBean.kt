package com.music961.mct_char_prac.bean

import java.io.Serializable


/*
* 채팅메시지는 사용자 이름에 따라 관리함
* 로컬에 저장하므로, 지난 메시지는 서버에서 받지 않음
* 앱을 새로 설치하면 저장한 데이터는 모두 제거됨.
* 서버에서 채팅 메시지를 받아오는 기능은 유료 구현 예정
* 채팅은 최대 255글자 제한
* 이미지 전송 가능. 공백이 아닐 경우 이미지를 스토리지 서버에서 파일전송한 후 보여줄것.
* */
data class ChatBean(
    val talk : String ,
    val time : Int ,
    val image : String
) : Serializable {
    companion object {
        private const val serialVersionUID = -90000059L
    }
}