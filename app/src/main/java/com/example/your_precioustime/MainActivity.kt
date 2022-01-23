package com.example.your_precioustime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.your_precioustime.Model.Bus
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

    private var mainActivitybinding : ActivityMainBinding? = null
    private val binding get() = mainActivitybinding!!

    private val retrofitInterface: Retrofit_InterFace = Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(
        Retrofit_InterFace::class.java)
    //hi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivitybinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MainLogoDd.setOnClickListener {
//            val intent = Intent(this,BusSubwayActivity::class.java)
            val intent = Intent(this,SecondActivity::class.java)
//
            startActivity(intent)
//            Toast.makeText(this,"gdgd",Toast.LENGTH_SHORT).show()
        }

        binding.mapGobtn.setOnClickListener {
//            val intent = Intent(this,MapsActivity::class.java)
//            startActivity(intent)
            retrofitcall()
        }


    }

    private fun retrofitcall() {
        val call =retrofitInterface.BusGet("25","DJB8001793")

        call.enqueue(object :retrofit2.Callback<Bus>{
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }


}