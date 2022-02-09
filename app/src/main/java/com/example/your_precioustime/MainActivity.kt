package com.example.your_precioustime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.ActivityListPackage.FavoriteActivity.BusFavroite_DeepInfo.Bus_FavroiteDeepInfo_Activity


import com.example.your_precioustime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mainActivitybinding: ActivityMainBinding? = null
    private val binding get() = mainActivitybinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivitybinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MainLogoDd.setOnClickListener {
            val intent = Intent(this, RealMainListActivity::class.java)
            startActivity(intent)
            //2월 7일 실험 푸쉬하고 받아봐한번ㅇㅇ
        }

    }

}