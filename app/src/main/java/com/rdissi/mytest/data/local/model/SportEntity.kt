package com.rdissi.mytest.data.local.model

import com.google.gson.annotations.SerializedName

data class SportEntity (

    @SerializedName("sportId")
    val id: Int,

    val name: String
)