package com.example.your_precioustime.ThridActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.databinding.ActivityBusSubwayBinding

class BusSubwayActivity : AppCompatActivity() {
    private var busSubwayActivityBinding : ActivityBusSubwayBinding? =null
    private val binding get() = busSubwayActivityBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        busSubwayActivityBinding = ActivityBusSubwayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        busintentcall()

    }

    private fun busintentcall() {
        val busnum = intent.getStringExtra("busnum")
        val arrivestation = intent.getStringExtra("arriveStation")

        binding.busnumTextView.text = busnum
        binding.arrivestationTextView.text= arrivestation

    }
}