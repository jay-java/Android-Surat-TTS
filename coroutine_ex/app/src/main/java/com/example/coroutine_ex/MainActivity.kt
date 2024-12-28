package com.example.coroutine_ex

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coroutine_ex.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnId.setOnClickListener{
            increseCounter()
        }
        binding.coroutineBtnId.setOnClickListener {
            longRunningTask()
        }
    }

    private fun longRunningTask() {
//        thread(start = true) {
//            for(i in 1..1000000000L){
//
//            }
//        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG,"1 - ${Thread.currentThread().name}")
        }
        GlobalScope.launch (Dispatchers.Main){
            Log.d(TAG,"2 - ${Thread.currentThread().name}")
        }
        MainScope().launch (Dispatchers.Default){
            Log.d(TAG,"3 - ${Thread.currentThread().name}")
        }
    }

    private fun increseCounter() {
        Log.d(TAG,"${Thread.currentThread().name}")
        binding.textId.text= "${binding.textId.text.toString().toInt()+1}"
    }
}