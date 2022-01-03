package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Url.Companion.SEOUL_SUBWAY_MAIN_URL
import com.example.your_precioustime.databinding.SubwayFragmentBinding

class SubwayFragment : Fragment(R.layout.subway_fragment) {

    private var subwayFragment: SubwayFragmentBinding? = null
    private val binding get() = subwayFragment!!

    private var retrofitInterface = Retrofit_Client.getJsonClienet(SEOUL_SUBWAY_MAIN_URL)
        .create(Retrofit_InterFace::class.java)

    lateinit var subwayAdapter: SubwayAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subwayFragment = SubwayFragmentBinding.bind(view)

        setRecyclerView()


    }

    private fun setRecyclerView() {
        subwayAdapter = SubwayAdapter()
        binding.subwayRecyclerView.apply {
            adapter = subwayAdapter
            layoutManager = LinearLayoutManager(context)
            subwayAdapter.submitList(
                listOf(
                    SubwayItem("할롱", "하이요", "잉잉"),
                    SubwayItem("할롱", "하이요", "잉잉"),
                    SubwayItem("할롱", "하이요", "잉잉")
                )
            )
        }

    }
}