package com.example.your_precioustime.SecondActivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.R
import kotlinx.android.synthetic.main.bus_fragment.*

class BusFragment:Fragment(R.layout.bus_fragment) {

    lateinit var upAdpater : UpAdpater


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ClickSearchBtn()




    }

    private fun ClickSearchBtn() {
        clickhere.setOnClickListener {
            val BusStopName = RealSearchEditText.text.toString()
            BusStopName_TextView.text= BusStopName
            GoawayTextView.visibility = View.INVISIBLE
            SetRecyclerView()
        }

    }

    private fun SetRecyclerView() {
        upAdpater = UpAdpater()
        busRecyclerView.apply {
            adapter=upAdpater
            upAdpater.submitList(listOf(BusItem("gdgd","gdgdg")))
            layoutManager = LinearLayoutManager(context)
        }
    }
}