package com.aravindh.andriodbasesetup.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aravindh.andriodbasesetup.utils.DEPARTMENT

@Entity(tableName = "tbl_student")
data class Student(

    @PrimaryKey
    val studentId: Int = 0,

    val studentName: String?,

    val studentMobileNumber: String?,

    val studentEmailAddress: String?,

    val studentPassword: String?,

    val departmentId: Int?
)