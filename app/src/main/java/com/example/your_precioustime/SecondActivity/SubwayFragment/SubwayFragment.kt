package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.Model.SubwayModel.RealtimeArrival
import com.example.your_precioustime.Model.SubwayModel.SubwayModel
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Activity
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.SubwayDataBase
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.SubwayNameEntity
import com.example.your_precioustime.SecondActivity.SecondActivity
import com.example.your_precioustime.Url.Companion.SEOUL_SUBWAY_MAIN_URL
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.SubwayFragmentBinding
import kotlinx.android.synthetic.main.subway_fragment.*
import retrofit2.Call
import retrofit2.Response


@SuppressLint("StaticFieldLeak")
class SubwayFragment : AppCompatActivity() {

    private var subwayFragment: SubwayFragmentBinding? = null
    private val binding get() = subwayFragment!!

    private var isFabOpen = false

    private var retrofitInterface = Retrofit_Client.getJsonClienet(SEOUL_SUBWAY_MAIN_URL)
        .create(Retrofit_InterFace::class.java)

    lateinit var subwayAdapter: SubwayAdapter
    lateinit var subwayDataBase: SubwayDataBase

    lateinit var subwayNameListEntity: List<SubwayNameEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayFragment = SubwayFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subwayDataBase= SubwayDataBase.getinstance(this)!!

        ToggleSet()

        binding.clickhere.setOnClickListener {
            val searchtext = binding.SearchEditText2.text.toString()
            binding.subtitleTextView.text = searchtext
            getsubwayCall(searchtext)

            subwayfavroiteAddImageView.visibility= View.VISIBLE

        }

        binding.subwayfavroiteAddImageView.setOnClickListener {
            val subwayname = binding.subtitleTextView.text.toString()

            val mylist = SubwayNameEntity(
                id=null,
                subwayname
            )

            subwayinsert(mylist)

        }

//        testgetAll()







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
                var subwaymodel = mutableListOf<SubwayItem>()
                val resultsubwaymodel = mutableListOf<SubwayItem>()


                body?.let {
                    val hello = body.realtimeArrivalList!!

                    for (i in hello.indices) {
                        val firstsubwayId = hello.get(i).subwayId!!

                        val trainLineNm = hello.get(i).trainLineNm
                        val bstatnNm = hello.get(i).bstatnNm
                        val arvlMsg2 = hello.get(i).arvlMsg2

                        subwaymodel.add(
                            SubwayItem(firstsubwayId, trainLineNm, bstatnNm, arvlMsg2)
                        )

                    }

                    Log.d(TAG, "onResponse: $subwaymodel")

                    for (i in subwaymodel.indices) {
                        when (subwaymodel[i].subwayId) {
                            "1001" -> {
                                subwaymodel[i].subwayId = "1"
                            }
                            "1002" -> {
                                subwaymodel[i].subwayId = "2"
                            }

                            "1003" -> {
                                subwaymodel[i].subwayId = "3"
                            }

                            "1004" -> {
                                subwaymodel[i].subwayId = "4"
                            }

                            "1005" -> {
                                subwaymodel[i].subwayId = "5"
                            }
                            "1006" -> {
                                subwaymodel[i].subwayId = "6"
                            }

                            "1007" -> {
                                subwaymodel[i].subwayId = "7"
                            }
                            "1008" -> {
                                subwaymodel[i].subwayId = "8"
                            }

                            "1009" -> {
                                subwaymodel[i].subwayId = "9"
                            }

                            "1063" -> {
                                subwaymodel[i].subwayId = "경"
                            }

                            "1065" -> {
                                subwaymodel[i].subwayId = "공"
                            }

                            "1067" -> {
                                subwaymodel[i].subwayId = "경춘"
                            }


                            "1075" -> {
                                subwaymodel[i].subwayId = "수"
                            }


                            "1077" -> {
                                subwaymodel[i].subwayId = "신"
                            }

                            "1091" -> {
                                subwaymodel[i].subwayId = "자"
                            }

                            "1092" -> {
                                subwaymodel[i].subwayId = "우"
                            }

                        }
                    }


                    setRecyclerView(subwaymodel)

                }

            }

            override fun onFailure(call: Call<SubwayModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })

    }

    private fun ToggleSet(){


        binding.floatingBtn.setOnClickListener {
            if(isFabOpen){
                ObjectAnimator.ofFloat(binding.BusfloatBtn,"translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(binding.SubwayFloatBtn,"translationY", 0f).apply { start() }
            }
            else{
                ObjectAnimator.ofFloat(binding.BusfloatBtn,"translationY", -150f).apply { start() }
                ObjectAnimator.ofFloat(binding.SubwayFloatBtn,"translationY", -300f).apply { start() }
            }

            isFabOpen = !isFabOpen
        }


        binding.SubwayFloatBtn.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

        binding.BusfloatBtn.setOnClickListener {
            val intent = Intent(this,Bus_Activity::class.java)
            startActivity(intent)
        }


    }

    private fun subwayinsert(subwayNameEntity: SubwayNameEntity){
        val insertTask = (object: AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                subwayNameListEntity = subwayDataBase.subwayNameDAO().subwayGetAll()

                Log.d(TAG, "지하철이름저장 로그: $subwayNameListEntity")

                val subwaynameList = mutableListOf<String>()
                for(i in subwayNameListEntity.indices){
                    val subwayname = subwayNameListEntity.get(i).subwayName
                    subwaynameList.add(subwayname)
                }

                if(binding.subtitleTextView.text !in subwaynameList){
                    subwayDataBase.subwayNameDAO().subwayInsert(subwayNameEntity)
                }

            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                val stationnameList = mutableListOf<String>()

                for(i in subwayNameListEntity.indices){
                    val stationname = subwayNameListEntity.get(i).subwayName
                    stationnameList.add(stationname)
                }

                if(binding.subtitleTextView.text in stationnameList){
                    Toast.makeText(this@SubwayFragment,"이미 즐겨찾기에 추가된 역입니다!",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@SubwayFragment,"즐겨찾기에 추가 되었습니다!",Toast.LENGTH_SHORT).show()
                    binding.subwayfavroiteAddImageView.setImageResource(R.drawable.fullstar)
                }

            }

        }).execute()
    }

    private fun testgetAll(){
        val getAllTask = (object :AsyncTask<Unit,Unit,Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                subwayNameListEntity = subwayDataBase.subwayNameDAO().subwayGetAll()
                Log.d(TAG, "지하철이름저장 로그: $subwayNameListEntity")
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val stationnameList = mutableListOf<String>()

                for(i in subwayNameListEntity.indices){
                    val stationname = subwayNameListEntity.get(i).subwayName
                    stationnameList.add(stationname)
                }

                if(binding.subtitleTextView.text in stationnameList){
                    binding.subwayfavroiteAddImageView.setImageResource(R.drawable.fullstar)
                }else{
                    binding.subwayfavroiteAddImageView.setImageResource(R.drawable.star)
                }

            }

        }).execute()
    }
}