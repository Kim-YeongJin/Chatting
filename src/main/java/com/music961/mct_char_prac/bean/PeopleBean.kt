package com.music961.mct_char_prac.bean

import java.io.Serializable

data class PeopleBean (
    val name : String,
    val latitude : Double,
    val longitude : Double,
    val age : Byte,
    val gender : Boolean,
    val time : Int,
    val talk : String,
    val face : String
): Serializable {
    companion object {
        private const val serialVersionUID = -90006569L
    }
}
