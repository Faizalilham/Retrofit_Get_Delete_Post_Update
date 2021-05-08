package com.example.week16.FullAPI

import com.example.week16.Model.Person
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface APIpoint {
    @GET("person")
    fun getallperson():Call<ListResponse<Person>>

    @GET("person/{id}")
    fun getbyid(@Path("id")id:Int):Call<SingleResponse<Person>>

    @DELETE("person/{id}")
    fun deletebyid(@Path("id")id : Int):Call<Void>
}

data class ListResponse<T>(
    var message : String,
    var status : Int,
    var data : List<T>
)

data class SingleResponse<T>(
        var message : String,
        var status : Int,
        var data : T
)


