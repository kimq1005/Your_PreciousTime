package com.example.your_precioustime.SecondActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.R
import com.example.your_precioustime.databinding.SubwayFragmentBinding

class SubwayFragment:Fragment(R.layout.subway_fragment) {
    private var setbinding : SubwayFragmentBinding? =null
    private val binding get() = setbinding!!

    lateinit var subWayAdapter: SubWayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setbinding = SubwayFragmentBinding.bind(view)

        setRecyclerView()

    }

    private fun setRecyclerView()=with(binding) {

        val hello:String = ""
        subWayAdapter = SubWayAdapter()
        val hi = listOf(
            SubwayItem("매탄권선","역5")
        )

        clickhere.setOnClickListener {
            SubwayStopNameTextView.text = RealSearchEditText.text.toString()
            RealSearchEditText.setText("")

            subwayRecyclerView.apply {
                adapter = subWayAdapter
                subWayAdapter.submitList(hi)
                layoutManager = LinearLayoutManager(context)


            }

        }



    }
}