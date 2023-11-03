package ipt.dam.api.retrofit.service

import ipt.dam.api.model.APIResult
import ipt.dam.api.model.Note
import ipt.dam.api.model.TokenJWT
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NoteService {
    @GET("API/getNotes.php")
    fun list(): Call<List<Note>>

    @GET("API/getNotesBA.php")
    fun listBA(@Header("Authorization") authorization: String): Call<List<Note>>


    @GET("API/getNotesJWT.php")
    fun listJWT(@Header("Authorization") token: String): Call<List<Note>>

    @FormUrlEncoded
    @POST("API/loginJWT.php")
    fun loginJWT(@Field("username") username: String?,
                 @Field("password") password: String?): Call<TokenJWT>

    @FormUrlEncoded
    @POST("API/addNote.php")
    fun addNote(@Field("title") title: String?,
                @Field("description") description: String?): Call<APIResult>

}