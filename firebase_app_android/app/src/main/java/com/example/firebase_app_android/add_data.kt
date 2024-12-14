package com.example.firebase_app_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_app_android.databinding.ActivityAddDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_data : AppCompatActivity() {

    private  lateinit var binding:ActivityAddDataBinding

    private  lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        binding.addBTN.setOnClickListener {
            saveUsers()
        }
    }

    private fun saveUsers() {
        val name = binding.nameEdt.text.toString()
        val contact = binding.contactEdt.text.toString()
        val address = binding.addressEdt.text.toString()

        print(name+contact+address)
        if(name.isEmpty()){
            binding.nameEdt.error = "Please enter name"
        }
        if(contact.isEmpty()){
            binding.contactEdt.error = "Please enter contact"
        }
        if(address.isEmpty()){
            binding.addressEdt.error = "Please enter Address"
        }

        val user_id= databaseReference.push().key!!

        val user =  UsersModel(user_id,name,contact,address)

        databaseReference.child(user_id).setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_SHORT).show()
                binding.nameEdt.text.clear()
                binding.contactEdt.text.clear()
                binding.addressEdt.text.clear()
            }
            .addOnFailureListener {
                Toast.makeText(this,"err : ${it.message}", Toast.LENGTH_SHORT).show()
            }

    }
}