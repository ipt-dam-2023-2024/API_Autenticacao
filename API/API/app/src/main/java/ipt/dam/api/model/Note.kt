package ipt.dam.api.model

/*
class Note(val title: String, val description: String)
*/


import com.google.gson.annotations.SerializedName
data class Note (
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?)