package com.example.lasttry

data class  Getdata (
    val reg_no: Int,
    val GPA:Double,
    val Grade: String,
    val course_code : String,
    val course_title: String,
    val semester : String,
    val course_credit: Float
)
data class StudentInfo(
    val name : String,
    val reg_no: Int,
    val date_of_birth :String,
    val dept : String,
    val session: String
)