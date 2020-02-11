package com.aravindh.andriodbasesetup.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aravindh.andriodbasesetup.utils.DEPARTMENT


/**
 *Created by Aravindh S on 10-02-2020.
 */



@Entity(tableName = "tbl_department")
data class Department(

    @PrimaryKey
    val departmentId: Int?,

    val departmentName: String?
)
