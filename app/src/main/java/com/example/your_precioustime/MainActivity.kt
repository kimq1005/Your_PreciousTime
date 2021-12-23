package com.example.your_precioustime

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.ThridActivity.BusSubwayActivity
import com.example.your_precioustime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mainActivitybinding : ActivityMainBinding? = null
    private val binding get() = mainActivitybinding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivitybinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.MainLogoDd.setOnClickListener {
//            val intent = Intent(this,BusSubwayActivity::class.java)
            val intent = Intent(this,SecondActivity::class.java)

            startActivity(intent)
//            Toast.makeText(this,"gdgd",Toast.LENGTH_SHORT).show()
            //하 인생
        }

    }

    private fun nextfadePageGO(activity: Activity) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }




}