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
        mainActivitybinding = ActivityMainBinding.inflate(layoutInflater) // getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의 // 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(binding.root)
//        Handler().postDelayed({
//                nextfadePageGO(this)
//            },2000)

        binding.MainLogoDd.setOnClickListener {
            val intent = Intent(this,BusSubwayActivity::class.java)
//            val intent = Intent(this,SecondActivity::class.java)

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