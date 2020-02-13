package com.music961.mct_char_prac.bean

import java.io.Serializable

/*
* 일러두기 : TalkBean 은 '한마디'에 있는 글을 중심으로 담고 있음.
* PeopleBean 을 HashMap 에 넣고, UsrKey 를 이용해서 찾을것.
* */
data class BbsBean(
    val talk : String ,
    val time : Int,
    val photo : String
) : Serializable {
    companion object {
        private const val serialVersionUID = -90000005L
    }
}