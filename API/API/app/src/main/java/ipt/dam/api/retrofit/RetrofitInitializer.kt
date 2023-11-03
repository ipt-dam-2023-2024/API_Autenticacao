package ipt.dam.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ipt.dam.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val gson: Gson = GsonBuilder().setLenient().create()
    private val host = "http://ram.ipt.pt/"
    //private val host = "http://10.0.2.2/"



    private val retrofit = Retrofit.Builder()
        .baseUrl(host)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun noteService() = retrofit.create(NoteService::class.java)
}