package com.aravindh.andriodbasesetup.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.aravindh.andriodbasesetup.database.entities.Department

/**
 *Created by aravindh.s on 10-02-2020.
 */

@Dao
interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(department: ArrayList<Department>)

}