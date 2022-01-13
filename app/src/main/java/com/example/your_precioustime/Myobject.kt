package com.example.your_precioustime

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Activity
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayFragment
import kotlin.coroutines.coroutineContext

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

//    fun FVfloatBtnClick(floatbtn: View){
//        floatbtn.setOnClickListener {
//            val intent = Intent(App.instance,SecondActivity::class.java)
//            App.instance.startActivity(intent)
//        }
//
//    }




}
