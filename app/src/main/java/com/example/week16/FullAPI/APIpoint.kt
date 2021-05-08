package com.example.week16.FullAPI

import com.example.week16.Model.Person
import retrofit2.Call
import retrofit2.http.*

interface APIpoint {
    @GET("person")
    fun getallperson():Call<ListResponse<Person>>

    @GET("person/{id}")
    fun getbyid(@Path("id")id:Int):Call<SingleResponse<Person>>

    @DELETE("person/{id}")
    fun deletebyid(@Path("id")id : Int):Call<Void>

    @FormUrlEncoded
    @POST("person")
    fun PostData(@Field("first_name")first_name : String,
    @Field("last_name")last_name : String,
    @Field("email")email :String) : Call<ListResponse<Person>>

    @FormUrlEncoded
    @PUT("person/{id}")
    fun UpdatePost(@Path("id")id:Int,
                 @Field("first_name")first_name : String,
                 @Field("last_name")last_name : String,
                 @Field("email")email :String) : Call<SingleResponse<Person>>

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


