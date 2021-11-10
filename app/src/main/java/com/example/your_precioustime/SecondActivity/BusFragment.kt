package com.example.your_precioustime.SecondActivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.R
import com.example.your_precioustime.databinding.BusFragmentBinding


class BusFragment:Fragment(R.layout.bus_fragment) {

    lateinit var upAdpater : UpAdpater
    private var busbinding : BusFragmentBinding? =null
    private val binding get() = busbinding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        busbinding = BusFragmentBinding.bind(view)


        ClickSearchBtn()




    }

    private fun ClickSearchBtn() =with(binding) {
        clickhere.setOnClickListener {
            val BusStopName = RealSearchEditText.text.toString()
            BusStopNameTextView.text= BusStopName
            GoawayTextView.visibility = View.INVISIBLE
            SetRecyclerView()
        }

    }

    private fun SetRecyclerView()=with(binding) {

        val hi = listOf(
            BusItem("99","4"),
            BusItem("62-1","2"),
            BusItem("92-1","5")
        )
        upAdpater = UpAdpater()
        busRecyclerView.apply {
            adapter=upAdpater
            upAdpater.submitList(hi)
            layoutManager = LinearLayoutManager(context)
        }
    }
}


