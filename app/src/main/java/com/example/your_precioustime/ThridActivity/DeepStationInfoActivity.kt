package com.example.your_precioustime.ThridActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.DB.BUSStationNameDataBase
import com.example.your_precioustime.SecondActivity.DB.BUSStationNameEntity
import com.example.your_precioustime.SecondActivity.UpAdpater
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.ActivityDeepStationInfoBinding
import retrofit2.Call
import retrofit2.Response
@SuppressLint("StaticFieldLeak")
class DeepStationInfoActivity : AppCompatActivity() {

    private var deepStationbinding:ActivityDeepStationInfoBinding? =null
    private val binding get() = deepStationbinding!!

    private lateinit var upAdpater:UpAdpater


    lateinit var busstationnameDB : BUSStationNameDataBase
    lateinit var busstationnameEntity : List<BUSStationNameEntity>
    //일단주석이다


    private val retrofitInterface: Retrofit_InterFace = Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepStationbinding = ActivityDeepStationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stationName = intent.getStringExtra("stationName").toString()
        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()


//        Log.d(TAG, "onCreate: $stationName , $stationNodeNumber")

        binding.BusStationName.text = stationName
        binding.backbtn.setOnClickListener {
            onBackPressed()
        }

        SetBusStationRecyclerView()

        FavoriteStation()




    }

    private fun FavoriteStation()=with(binding) {
        var checkBoolean=true
        var dbBoolean:Boolean

        countingstars.setOnClickListener {
            if(checkBoolean==true){
                countingstars.setImageResource(R.drawable.shinigstar)
                checkBoolean=false

                if(checkBoolean==false){
                    dbBoolean=true
                    if(dbBoolean==true){

                    }
                }

//                val stationname = BusStationName.text.toString()
//
//                val busDblist =
//                    BUSStationNameEntity(
//                        id = null,
//                        citycode = "31010",
//                        busStationName = stationname
//                )
//
//                BUSStationNameInsert(busDblist)
//
//                Log.d(TAG, "FavoriteStation: $checkBoolean")

            }else{
                countingstars.setImageResource(R.drawable.star)
                //DB삭제
                checkBoolean=true
            }

        }

    }

    private fun SetBusStationRecyclerView()=with(binding) {
        val stationName = intent.getStringExtra("stationName").toString()
        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
        val citycode:String = "31010"
        val call = retrofitInterface.BusGet(citycode,stationNodeNumber)
        call.enqueue(object:retrofit2.Callback<Bus>{
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                upAdpater = UpAdpater()

                val body = response.body()

                body?.let{

                    val wow = body.body.items.item

                    val hello =body.body.items.item


                    val hi = mutableListOf<Item>()

                    for(i in hello.indices){
                        val busNm:String
                        val waitbus:Int

                        busNm = hello.get(i).routeno!!
                        waitbus = hello.get(i).arrprevstationcnt!!
                        hi.add(Item(
                            busNm,waitbus
                        ))

                    }
                    Log.d(TAG, "\n 전체값 리스트다 질문받는다: $hi \n")

                    Log.d(TAG, "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n")

                    val firstList= hi.filterIndexed { index, i ->
//                        Log.d(TAG, "인덱스값이 뭔지 확인하기 : $index , $i")
                        index % 2 ==0    //이건 그냥 말그대로 짝수만을 가져온거야
                    }

                    val secondList = hi.filterIndexed{index, item ->
                        index % 2 ==1
                    }

//                    Log.d(TAG, "fristList다: ${firstList}\n")
//                    Log.d(TAG, "secondList다: ${secondList}")

                    val ResultList = mutableListOf<Item>()

                    firstList.forEach {
                        val ARouteNo = it.routeno
                        val AWaittime = it.arrprevstationcnt
                        var found = false

//            Log.d(TAG, "onCreate: $AWaittime")


                        secondList.forEach {
                            val BRouteNo = it.routeno
                            val BWaittime = it.arrprevstationcnt


                            if(ARouteNo==BRouteNo){
                                if(AWaittime!! > BWaittime!!){
//                                    Log.d(TAG, "onCreate: $it")
                                    ResultList.add(Item(it.routeno,it.arrprevstationcnt))

                                }else{
                                    ResultList.add(Item(ARouteNo,AWaittime))
                                }

                                Log.d(TAG, "지막 그거여 확인혀: $ResultList")
                            }


                        }

                        deepstationinfoRecyclerView.apply {
                            adapter = upAdpater
                            layoutManager = LinearLayoutManager(context)
                            upAdpater.submitList(ResultList)
                        }


                    }




                }

            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })




    }

    private fun BUSStationNameInsert(busStationNameEntity: BUSStationNameEntity){

        busstationnameDB = BUSStationNameDataBase.getinstance(App.instance)!!


        var businsertTask = (object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busstationnameDB.busstationnameDao().busStationNameInsert(busStationNameEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                busstationnameGetAll()
            }
        }).execute()

    }

    private fun busstationnameGetAll(){
        val busGetAllTask = (object: AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busstationnameEntity = busstationnameDB.busstationnameDao().busStationNameGetAll()
            }
        }).execute()
    }


}