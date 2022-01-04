package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.Model.SubwayModel.RealtimeArrival
import com.example.your_precioustime.Model.SubwayModel.SubwayModel
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Url.Companion.SEOUL_SUBWAY_MAIN_URL
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.SubwayFragmentBinding
import retrofit2.Call
import retrofit2.Response

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

        binding.clickhere.setOnClickListener {
            val searchtext = binding.SearchEditText.text.toString()
            getsubwayCall(searchtext)
        }

//        setRecyclerView()


    }

    private fun setRecyclerView(subwayItem: List<SubwayItem>) {
        subwayAdapter = SubwayAdapter()
        binding.subwayRecyclerView.apply {
            adapter = subwayAdapter
            layoutManager = LinearLayoutManager(context)
            subwayAdapter.submitList(subwayItem)
        }

    }

    private fun getsubwayCall(statNm: String) {

        val call = retrofitInterface.SUBWAYGET(
            statnNm = statNm
        )

        call.enqueue(object : retrofit2.Callback<SubwayModel> {
            override fun onResponse(call: Call<SubwayModel>, response: Response<SubwayModel>) {
                val body = response.body()
                val subwaymodel = mutableListOf<SubwayItem>()

                body?.let {
                    val hello = body.realtimeArrivalList!!

                    for (i in hello.indices) {

                        val trainLineNm = hello.get(i).trainLineNm
                        val bstatnNm = hello.get(i).bstatnNm
                        val barvlDt = hello.get(i).barvlDt

                        subwaymodel.add(
                            SubwayItem(trainLineNm,bstatnNm,barvlDt)
                        )

                        setRecyclerView(subwaymodel)


                    }

                }

            }

            override fun onFailure(call: Call<SubwayModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })

    }
}