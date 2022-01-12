package com.example.your_precioustime.SecondActivity.subwayfavorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.databinding.ActivitySubwayFavroiteDeevInfoBinding

class SubwayFavroiteDeevInfo : AppCompatActivity() {

    var subwayFavroiteDeevInfoBinding : ActivitySubwayFavroiteDeevInfoBinding? =null
    val binding get() = subwayFavroiteDeevInfoBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayFavroiteDeevInfoBinding = ActivitySubwayFavroiteDeevInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}