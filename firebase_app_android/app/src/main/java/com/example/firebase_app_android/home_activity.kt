package com.example.firebase_app_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase_app_android.databinding.ActivityHomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class home_activity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        val userName = binding.userNameTextId

        val auth = Firebase.auth
        val user = auth.currentUser

        if(user !=null){
            val user_name =  user.displayName
            userName.text = "Welcome, "+user_name
        }
        else{
            Toast.makeText(this,"MSG",Toast.LENGTH_SHORT).show()
        }


        binding.insertBTN.setOnClickListener {
            val intent = Intent(this,add_data::class.java)
            startActivity(intent)
        }

        binding.fetchBTN.setOnClickListener {
            val intent = Intent(this,fetch_data::class.java)
            startActivity(intent)
        }

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()

            googleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this,Login::class.java)
                startActivity(intent)
                finish()
            }

        }

    }
}