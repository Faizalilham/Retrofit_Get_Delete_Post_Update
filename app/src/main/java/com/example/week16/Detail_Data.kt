package com.example.week16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week16.FullAPI.APIClient
import com.example.week16.FullAPI.APIpoint
import com.example.week16.FullAPI.ListResponse
import com.example.week16.FullAPI.SingleResponse
import com.example.week16.Model.Person
import com.example.week16.databinding.ActivityDetailDataBinding
import com.example.week16.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail_Data : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getdatafromactivity()
        delete()
        back()


    }

    private fun getdatafromactivity(){
        APIClient.APIEndpoint().getbyid(intent.getIntExtra("id",1))
                .enqueue(object : Callback<SingleResponse<Person>>{
                    override fun onResponse(call: Call<SingleResponse<Person>>, response: Response<SingleResponse<Person>>) {
                        if(response.isSuccessful){
                            val data = response.body()
                            if(data!= null){
                                showview(data.data)
                            }

                        }
                    }

                    override fun onFailure(call: Call<SingleResponse<Person>>, t: Throwable) {
                        println(t.message)
                    }
                })
    }
    private fun showview(post : Person){
      binding.detailfirstname.text = post.first_name
      binding.detaillastname.text = post.last_name
      binding.detailemail.text = post.email
      binding.detailcreate.text = post.createdAt
      binding.detailupdate.text = post.updatedAt
    }
    private fun deletedata(){
        APIClient.APIEndpoint().deletebyid(intent.getIntExtra("id",0))
                .enqueue(object : Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if(response.isSuccessful){
                            Toast.makeText(applicationContext,"Successfully Deleted Data",Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                       println(t.message)
                    }


                })
    }
    private fun delete(){
        binding.imgdelete.setOnClickListener {
            deletedata()
            finish()
        }

    }
    private fun back(){
        binding.back.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

}