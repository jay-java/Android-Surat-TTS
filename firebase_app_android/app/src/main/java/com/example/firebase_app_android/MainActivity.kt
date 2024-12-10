package com.example.firebase_app_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_app_android.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textLoginId.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


        binding.signupBtn.setOnClickListener {
            val email = binding.emailEditeText.text.toString()
            val pass = binding.passEditeText.text.toString()
            val cp = binding.cpassEditeText.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && cp.isNotEmpty()){
                if(pass == cp){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,Login::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Pass CP not matched",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Blank fields",Toast.LENGTH_SHORT).show()
            }
        }


    }
}