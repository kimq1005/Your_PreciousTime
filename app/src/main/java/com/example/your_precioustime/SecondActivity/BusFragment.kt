package com.example.your_precioustime.SecondActivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Retrofit.Retrofit_Manager
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.BusFragmentBinding
import retrofit2.Call
import retrofit2.Response


class BusFragment:Fragment(R.layout.bus_fragment) {

    lateinit var upAdpater : UpAdpater
    private var busbinding : BusFragmentBinding? =null
    private val binding get() = busbinding!!

    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        busbinding = BusFragmentBinding.bind(view)

//        Retrofit_Manager.retrofitManager.GETBUS()
        ClickSearchBtn()


    }

    private fun ClickSearchBtn() =with(binding) {


        clickhere.setOnClickListener {
            val BusStopName = RealSearchEditText.text.toString()
            BusStopNameTextView.text= BusStopName
            SetRecyclerView()

        }

    }

    private fun SetRecyclerView()=with(binding) {

        val hi = listOf(
            Item(null,null)

        )
        upAdpater = UpAdpater()
//        busRecyclerView.apply {
//            adapter=upAdpater
//            upAdpater.submitList(hi)
//            layoutManager = LinearLayoutManager(context)
//        }

        val call = retrofitInterface.BusGet("25","DJB8001793")

        call.enqueue(object:retrofit2.Callback<Bus>{
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                val body = response.body()
                val higg = body?.body?.items
                Log.d(TAG, "onResponse: ${higg}")
                body?.let{Bus->

                    busRecyclerView.apply {
                        adapter=upAdpater
                        upAdpater.submitList(Bus.body.items.item)
                        layoutManager = LinearLayoutManager(context)
                    }

                }
            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })


    }
}


