package com.example.your_precioustime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Activity
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayFragment
import com.example.your_precioustime.databinding.ActivityMainListBinding
import com.example.your_precioustime.databinding.ActivityRealMainListBinding

class RealMainListActivity : AppCompatActivity() {

    private var realMainListBinding : ActivityRealMainListBinding?=null
    private val binding get() = realMainListBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realMainListBinding = ActivityRealMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.busView.setOnClickListener {
            val intent = Intent(this,Bus_Activity::class.java)
            startActivity(intent)
        }

        binding.SubwayView.setOnClickListener {
            val intent = Intent(this,SubwayFragment::class.java)
            startActivity(intent)
        }

        binding.FavroiteView.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }


    }
}