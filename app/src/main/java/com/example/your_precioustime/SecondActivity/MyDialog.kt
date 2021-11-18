package com.example.your_precioustime.SecondActivity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.BusitemLayoutBinding
import com.example.your_precioustime.databinding.MydialogBinding

class MyDialog(context: Context):Dialog(context) {

    private var mydialogBinding:MydialogBinding? =null
    private var upAdpaterBinding:BusitemLayoutBinding? = null
    private val binding get() = mydialogBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mydialogBinding = MydialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.YesBtn.setOnClickListener {
//            Toast.makeText(context,"저장되었습니다.",Toast.LENGTH_SHORT).show()
//            onBackPressed()
        }

//        binding.NoBtn.setOnClickListener {
//            Toast.makeText(context,"취소되었습니다.",Toast.LENGTH_SHORT).show()
//            onBackPressed()
//        }


    }
}