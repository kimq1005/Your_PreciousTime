package com.example.your_precioustime

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.ObjectManager.citycodeSaveClass
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.TestMapActivity.MapsActivity
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {

    private var mainActivitybinding: ActivityMainBinding? = null
    private val binding get() = mainActivitybinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivitybinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MainLogoDd.setOnClickListener {
            Handler().postDelayed({
                fadeanimation(this)
            }, 500)
        }

//        binding.fuckyousiballoma.setOnClickListener {
//            val fuckyou = binding.MainLogoDd.text.toString()
//            citycodeSaveClass.citycodeSaveClass.setString("citycode",fuckyou)
//        }


    }

    fun fadeanimation(activity: Activity) {
        val intent = Intent(this, RealMainListActivity::class.java)
        startActivity(intent)
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }


}