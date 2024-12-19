package com.example.firebase_app_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private var userList:ArrayList<UsersModel>):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_single_user,parent,false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textViewUserName.text = currentUser.userName
    }


    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textViewUserName:TextView = itemView.findViewById(R.id.tv_user_name)
    }
}