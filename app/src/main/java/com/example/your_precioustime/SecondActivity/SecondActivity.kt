package com.example.your_precioustime.SecondActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.R
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.bus_fragment.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ClickFragment()



    }

    private fun ClickFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.SecondFragment, BusFragment())
            .commit()

        BusInfo_Btn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.SecondFragment, BusFragment())
                .commit()
        }

        SubInfo_Btn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.SecondFragment,SubwayFragment())
                .commit()
        }


    }
}