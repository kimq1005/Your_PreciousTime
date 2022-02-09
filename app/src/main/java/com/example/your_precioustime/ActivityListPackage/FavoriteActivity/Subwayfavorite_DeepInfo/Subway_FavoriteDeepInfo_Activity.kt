package com.example.your_precioustime.ActivityListPackage.FavoriteActivity.Subwayfavorite_DeepInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.ActivityListPackage.SubwayActivity.SubwayAdapter
import com.example.your_precioustime.Model.SubwayModel.SubwayModel
import com.example.your_precioustime.ObjectManager.Myobject
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Retrofit.Retrofit_Manager
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.ActivitySubwayFavoriteDeepInfoBinding
import com.google.android.gms.common.api.Response
import javax.security.auth.callback.Callback


class Subway_FravoriteDeepInfo_Activity : AppCompatActivity() {

    private var SubwayFavroiteDeepInfoBinding: ActivitySubwayFavoriteDeepInfoBinding? = null
    private val binding get() = SubwayFavroiteDeepInfoBinding!!

    private var retrofitInterface = Retrofit_Client.getJsonClienet(Url.SEOUL_SUBWAY_MAIN_URL)
        .create(Retrofit_InterFace::class.java)


    lateinit var subwayAdapter: SubwayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SubwayFavroiteDeepInfoBinding =
            ActivitySubwayFavoriteDeepInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backbtn.setOnClickListener {
            onBackPressed()
            finish()
        }

        setRecyclearView()

        Myobject.myobject.ToggleSet(
            this,
            binding.floatingBtn,
            binding.FavroiteFloatBtn,
            binding.SubwayFloatBtn,
            binding.BusfloatBtn
        )

    }

    private fun setRecyclearView() {
        val subwayname = intent.getStringExtra("subwayname").toString()
        binding.SubwayStationName.text = subwayname
        Retrofit_Manager.retrofitManager.getsubwayCall(subwayname, mymodel = { subwaymodel ->
            subwayAdapter = SubwayAdapter()
            binding.SubwayFVDeepInFoRecyclerView.apply {
                adapter = subwayAdapter
                layoutManager = LinearLayoutManager(this@Subway_FravoriteDeepInfo_Activity)
                subwayAdapter.submitList(subwaymodel)
            }
        })

    }

}