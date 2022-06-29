package com.example.apklibreria.Api.ModelBooks

import com.google.gson.annotations.SerializedName


data class Books(

    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()

)
