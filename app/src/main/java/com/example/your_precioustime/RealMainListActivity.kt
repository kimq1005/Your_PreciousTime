package com.example.your_precioustime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.databinding.ActivityMainListBinding
import com.example.your_precioustime.databinding.ActivityRealMainListBinding

class RealMainListActivity : AppCompatActivity() {

    private var realMainListBinding : ActivityRealMainListBinding?=null
    private val binding get() = realMainListBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realMainListBinding = ActivityRealMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}