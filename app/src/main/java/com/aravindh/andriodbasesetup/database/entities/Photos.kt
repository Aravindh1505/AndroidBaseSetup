package com.aravindh.andriodbasesetup.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "photos_table")
@Parcelize
data class Photos(
    @Json(name = "image_name") val photoName: String,

    @PrimaryKey
    @Json(name = "image_path") val photoPath: String
) : Parcelable

fun List<Photos>.asDomainModel(): List<Photos> {
    return map {
        Photos(
            photoName = it.photoName,
            photoPath = it.photoPath
        )
    }
}