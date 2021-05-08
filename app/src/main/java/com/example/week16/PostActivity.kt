package com.example.week16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week16.FullAPI.APIClient
import com.example.week16.FullAPI.ListResponse
import com.example.week16.FullAPI.SingleResponse
import com.example.week16.Model.Person
import com.example.week16.databinding.ActivityPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ButtonSave()
        getdatafromactivity()
        ButtonUpdate()
    }
    private fun PostData(){
        val firstname = binding.ETFirstName.text.toString()
        val lastname = binding.ETLastName.text.toString()
        val email  = binding.ETEmail.text.toString()

        APIClient.APIEndpoint().PostData(firstname,lastname,email).enqueue(object : Callback<ListResponse<Person>>{
            override fun onResponse(call: Call<ListResponse<Person>>, response: Response<ListResponse<Person>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    Toast.makeText(applicationContext,"Succesfully Added Data",Toast.LENGTH_SHORT).show()
                    println("Succes add data "+body)
                }
            }

            override fun onFailure(call: Call<ListResponse<Person>>, t: Throwable) {
                println(t.message)
            }

        })
    }
    private fun ButtonSave(){
        binding.ButtonSave.setOnClickListener {
            PostData()
            finish()
        }
    }
    private fun getdatafromactivity(){
        binding.ETFirstName.setText(intent.getStringExtra("first_name"))
        binding.ETLastName.setText(intent.getStringExtra("last_name"))
        binding.ETEmail.setText(intent.getStringExtra("email"))
    }
    private fun UpdateData(){
        val id = intent.getIntExtra("id",1)
        val firstname = binding.ETFirstName.text.toString()
        val lastname = binding.ETLastName.text.toString()
        val email = binding.ETEmail.text.toString()
        APIClient.APIEndpoint().UpdatePost(id,firstname,lastname,email)
                .enqueue(object : Callback<SingleResponse<Person>>{
                    override fun onResponse(call: Call<SingleResponse<Person>>, response: Response<SingleResponse<Person>>) {
                        if(response.isSuccessful){
                            val body = response
                            Toast.makeText(applicationContext,"Successfully Updating Data",Toast.LENGTH_SHORT).show()
                            println("Succes Update data "+body)
                        }
                    }

                    override fun onFailure(call: Call<SingleResponse<Person>>, t: Throwable) {
                        println(t.message)
                    }

                })
    }
    private fun ButtonUpdate(){
        binding.ButtonnUpdate.setOnClickListener {
            UpdateData()
            finish()
        }
    }

}