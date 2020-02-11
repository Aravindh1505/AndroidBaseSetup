package com.aravindh.andriodbasesetup.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aravindh.andriodbasesetup.database.dao.DepartmentDao
import com.aravindh.andriodbasesetup.database.dao.PhotosDao
import com.aravindh.andriodbasesetup.database.dao.StudentDao
import com.aravindh.andriodbasesetup.database.entities.Department
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.database.entities.Student
import com.aravindh.andriodbasesetup.database.views.StudentViews

@Database(
    entities = [Student::class, Photos::class, Department::class],
    views = [StudentViews::class],
    version = 1,
    exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao

    abstract val departmentDao: DepartmentDao

    abstract val photosDao: PhotosDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database.db"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}