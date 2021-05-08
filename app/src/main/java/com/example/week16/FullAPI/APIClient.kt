package com.example.week16.FullAPI

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class APIClient {
    companion object{
        private var retrofit : Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        private fun getclient():Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(constant.Base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
        fun APIEndpoint() : APIpoint = getclient().create(APIpoint::class.java)
    }
}
class constant{
    companion object{
        const val Base_url = "https://mobile-squad-api.herokuapp.com/"
    }
}