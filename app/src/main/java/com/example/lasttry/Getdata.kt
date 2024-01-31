package com.example.lasttry

data class  Getdata (
    // Represents a data object containing information about a student's course.
    val reg_no: Int,
    val GPA:Double,
    val Grade: String,
    val course_code : String,
    val course_title: String,
    val semester : String,
    val course_credit: Float
)
data class StudentInfo(
    // Represents a data object containing information about a student.
    val name : String,
    val reg_no: Int,
    val date_of_birth :String,
    val dept : String,
    val session: String
)