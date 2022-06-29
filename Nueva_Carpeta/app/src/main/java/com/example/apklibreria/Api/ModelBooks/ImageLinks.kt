package com.example.apklibreria.Api.ModelBooks

import com.google.gson.annotations.SerializedName


data class ImageLinks(

    @SerializedName("smallThumbnail") var smallThumbnail: String? = "https://assets.stickpng.com/images/580b585b2edbce24c47b276b.png",
    @SerializedName("thumbnail") var thumbnail: String? = "https://assets.stickpng.com/images/580b585b2edbce24c47b276b.png"

)