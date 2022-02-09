package com.example.your_precioustime.ActivityListPackage.HelpActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {

    private var helpActivity:ActivityHelpBinding? =null
    private val binding get() = helpActivity!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helpActivity = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}