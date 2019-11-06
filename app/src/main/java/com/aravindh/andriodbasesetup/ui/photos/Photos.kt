package com.aravindh.andriodbasesetup.ui.photos

import com.squareup.moshi.Json

data class Photos(
    @Json(name = "image_name") val photoName: String,
    @Json(name = "image_path") val photoPath: String
)