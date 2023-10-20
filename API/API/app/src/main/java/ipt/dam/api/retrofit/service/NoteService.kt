package ipt.dam.api.retrofit.service

import ipt.dam.api.model.APIResult
import ipt.dam.api.model.Note
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteService {
    @GET("API/getNotes.php")
    fun list(): Call<List<Note>>

    @FormUrlEncoded
    @POST("API/addNote.php")
    fun addNote(@Field("title") title: String?,
                @Field("description") description: String?): Call<APIResult>

}