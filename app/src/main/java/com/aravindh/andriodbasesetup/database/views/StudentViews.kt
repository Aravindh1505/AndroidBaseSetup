package com.aravindh.andriodbasesetup.database.views

import androidx.room.DatabaseView
import com.aravindh.andriodbasesetup.database.entities.Department


/**
 *Created by Aravindh S on 10-02-2020.
 */

@DatabaseView(
    "SELECT tbl_student.studentId, tbl_student.studentName,tbl_department.departmentName FROM tbl_student" +
            " INNER JOIN tbl_department ON tbl_student.departmentId = tbl_department.departmentId")
data class StudentViews(
    val studentId: Int,
    val studentName: String,
    val departmentName: String
)