package com.aravindh.andriodbasesetup.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aravindh.andriodbasesetup.database.dao.PhotosDao
import com.aravindh.andriodbasesetup.database.dao.UserDao
import com.aravindh.andriodbasesetup.database.entities.Photos
import com.aravindh.andriodbasesetup.database.entities.User

@Database(entities = [User::class, Photos::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract val userDao: UserDao

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
                        "my_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}