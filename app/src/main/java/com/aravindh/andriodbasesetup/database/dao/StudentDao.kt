package com.aravindh.andriodbasesetup.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aravindh.andriodbasesetup.database.entities.Student
import com.aravindh.andriodbasesetup.database.views.StudentViews

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: ArrayList<Student>)

    @Update
    fun update(student: Student)

    @Query("select * from tbl_student where studentId = :userId")
    fun getUser(userId: Long): Student

    @Query("select * from tbl_student order by studentId desc")
    fun getAllUsers(): LiveData<List<Student>>

    @Query("select count(*) from tbl_student")
    fun getCount(): LiveData<Int>

    @Query("delete from tbl_student")
    fun clear()

    @Query("select * from studentviews")
    fun getStudentsBasedOnDepartment(): LiveData<List<StudentViews>>

}