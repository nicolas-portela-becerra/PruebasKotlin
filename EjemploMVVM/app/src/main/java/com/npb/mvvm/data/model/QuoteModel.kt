package com.npb.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    //La anotacion SerializedName se utiliza por si el nombre que se recibe del back no coincide con el nombre de la variable
    @SerializedName("quote") val quote : String,
    @SerializedName("author") val author : String
)
