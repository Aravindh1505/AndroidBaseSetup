package com.aravindh.andriodbasesetup.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,

    val userName: String,

    val userMobileNumber: Long,

    val userEmailAddress: String,

    val userPassword: String
)