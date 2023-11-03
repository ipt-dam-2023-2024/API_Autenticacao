package ipt.dam.api.model

import com.google.gson.annotations.SerializedName

data class TokenJWT (
    @SerializedName("token") val token: String?
)