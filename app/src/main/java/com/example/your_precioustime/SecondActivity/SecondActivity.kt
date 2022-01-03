package com.example.your_precioustime.SecondActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.your_precioustime.R
import com.example.your_precioustime.SecondActivity.Busfragment.BusFragment
import com.example.your_precioustime.SecondActivity.FavoriteFragment.FavroiteFragment
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayFragment
import com.example.your_precioustime.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private var secondBinding : ActivitySecondBinding? =null
    private val binding get() = secondBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ClickFragment()


    }

    private fun ClickFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.SecondFragment, BusFragment())
            .commit()

        binding.BusInfoBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.SecondFragment, BusFragment())
                .commit()
        }

        binding.SubwayinfoBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.SecondFragment , SubwayFragment())
                .commit()
        }


        binding.FavoriteInfoBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.SecondFragment, FavroiteFragment())
                .commit()
        }

    }
}