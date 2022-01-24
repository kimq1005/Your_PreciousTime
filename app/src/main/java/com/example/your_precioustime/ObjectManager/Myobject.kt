package com.example.your_precioustime.ObjectManager

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Activity
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Station_Search_Adapter
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayFragment
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util
import retrofit2.Call
import retrofit2.Response

class Myobject {



    companion object {
        val myobject = Myobject()
    }

    fun ToggleSet(context:Context,floatbtn: View, fvfloatBtn:View, subwayfloatbtn: View,busfloatbtn:View) {
        var myBolean = false
        floatbtn.setOnClickListener {
            if(myBolean == false){
                ObjectAnimator.ofFloat(fvfloatBtn, "translationY",-150f).apply { start() }
                ObjectAnimator.ofFloat(subwayfloatbtn,"translationY", -300f).apply { start() }
                ObjectAnimator.ofFloat(busfloatbtn, "translationY",-450f).apply { start() }
                myBolean=true

            }else{
                ObjectAnimator.ofFloat(fvfloatBtn, "translationY",-0f).apply { start() }
                ObjectAnimator.ofFloat(subwayfloatbtn,"translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(busfloatbtn, "translationY",0f).apply { start() }
                myBolean=false
            }
        }

        fvfloatBtn.setOnClickListener {
            val intent = Intent(context,SecondActivity::class.java)
            context.startActivity(intent)
        }

        subwayfloatbtn.setOnClickListener {
            val intent = Intent(context,SubwayFragment::class.java)
            context.startActivity(intent)
        }

        busfloatbtn.setOnClickListener {
            val intent = Intent(context,Bus_Activity::class.java)
            context.startActivity(intent)
        }


    }


    fun changeSubwayText(mytext:String){
        val hello:String
        if(mytext.contains("")){
            val one = mytext.replace("[", "")
            val two = one.replace("]", "")
            val substring = two.substring(0..5)
        }



    }


}
