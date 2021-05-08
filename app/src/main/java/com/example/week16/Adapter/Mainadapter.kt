package com.example.week16.Adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week16.Model.Person
import com.example.week16.R
import com.example.week16.databinding.ItemdataBinding


class Mainadapter(private var users : List<Person>,val listener : Click):RecyclerView.Adapter<Mainadapter.UserViewHolder>() {
    inner class UserViewHolder ( val binding : ItemdataBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemdataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            firstname.text = users[position].first_name
            firstname.setOnClickListener {
                listener.onClick(users[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
    interface Click{
        fun onClick (post : Person)
    }
}