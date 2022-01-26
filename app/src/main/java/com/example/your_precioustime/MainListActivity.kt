package com.example.your_precioustime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.databinding.ActivityMainListBinding

class MainListActivity : AppCompatActivity() {
    private var mainListBinding : ActivityMainListBinding?=null
    private val binding get() = mainListBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainListBinding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}