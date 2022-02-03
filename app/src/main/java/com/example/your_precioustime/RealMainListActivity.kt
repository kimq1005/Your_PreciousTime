package com.example.your_precioustime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.example.your_precioustime.GuideActivity.GuideActivity
import com.example.your_precioustime.NoticeActivity.NoticeActivity
import com.example.your_precioustime.ObjectManager.Myobject
import com.example.your_precioustime.ObjectManager.citycodeCallObject
import com.example.your_precioustime.ObjectManager.citycodeSaveClass
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Activity
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayFragment
import com.example.your_precioustime.databinding.ActivityMainListBinding
import com.example.your_precioustime.databinding.ActivityRealMainListBinding
import kotlinx.android.synthetic.main.activity_bus.*

class RealMainListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var realMainListBinding: ActivityRealMainListBinding? = null
    private val binding get() = realMainListBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realMainListBinding = ActivityRealMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.mySpinner
        spinner.onItemSelectedListener = this
        setSpinner(spinner)

        binding.busView.setOnClickListener {
            val mycitycode:String = citycodeCallObject.citycodeCallObject.citycode(binding.citynameTextView.text.toString())
            citycodeSaveClass.citycodeSaveClass.Savecitycode("citycode",mycitycode)
            val intent = Intent(this, Bus_Activity::class.java)
            startActivity(intent)
        }

        binding.SubwayView.setOnClickListener {
            val intent = Intent(this, SubwayFragment::class.java)
            startActivity(intent)
        }

        binding.FavroiteView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.guideView.setOnClickListener {
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }

        binding.noticeView.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)

        }


    }

    private fun setSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this,
            R.array.cityname,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        binding.citynameTextView.text = binding.mySpinner.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }




}