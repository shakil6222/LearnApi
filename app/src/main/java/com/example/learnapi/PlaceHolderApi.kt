package com.example.learnapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaceHolderApi {

    @GET("posts")
    fun getToList(): Observable<List<TodoDetailsModel>>

    @POST("posts")
    fun addPost(@Body postModel: TodoDetailsModel): Observable<TodoDetailsModel>

    companion object Factory {
        fun create(): PlaceHolderApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/") // Update base URL here
                .build()
                .create(PlaceHolderApi::class.java)
            return retrofit
        }
    }
}