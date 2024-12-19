package com.example.firebase_app_android

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase_app_android.databinding.ActivityFetchDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class fetch_data : AppCompatActivity() {
    private lateinit var binding: ActivityFetchDataBinding
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList:ArrayList<UsersModel>
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        userList = arrayListOf<UsersModel>()
        getUsersData()

    }

    private fun getUsersData() {
        binding.recyclerView.visibility = View.GONE
        binding.loadintTextId.visibility = View.VISIBLE

        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                if(snapshot.exists()){
                    for(userSnap in snapshot.children){
                        val userData = userSnap.getValue(UsersModel::class.java)
                        userList.add(userData!!)
                    }
                    val userAdapater = UserAdapter(userList)
                    binding.recyclerView.adapter = userAdapater

                    binding.recyclerView.visibility = View.VISIBLE
                    binding.loadintTextId.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}