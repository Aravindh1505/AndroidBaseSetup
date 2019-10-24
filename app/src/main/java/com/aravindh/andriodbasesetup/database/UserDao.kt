package com.aravindh.andriodbasesetup.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("select * from user_table where userId = :userId")
    fun getUser(userId: Long): User

    @Query("select * from user_table order by userId desc")
    fun getAllUsers(): LiveData<List<User>>

    @Query("delete from user_table")
    fun clear()
}