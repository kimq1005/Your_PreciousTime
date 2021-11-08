package com.example.your_precioustime

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed({
                nextfadePageGO(this)
            },2000)




    }

    private fun nextfadePageGO(activity: Activity) {

        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }


}