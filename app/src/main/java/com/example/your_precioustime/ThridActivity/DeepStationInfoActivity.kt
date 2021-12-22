package com.example.your_precioustime.ThridActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.DB.*
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

    lateinit var busFavoriteDB : BusFavroiteDataBase
    lateinit var activitybusfavoriteEntity: List<TestFavoriteModel>


    private val retrofitInterface: Retrofit_InterFace = Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepStationbinding = ActivityDeepStationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        busFavoriteDB = BusFavroiteDataBase.getinstance(App.instance)!!


        val stationName = intent.getStringExtra("stationName").toString()
        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()

        // 야 만약에 이페이지에서 별을 누르면 stationName, stationNodeNumber를 저장하면 되잖아 그리고 헷갈림을 방지하기위해서 일단 별모양 대신에 즐겨찾기 버튼이라거 하자 ㅇㅋ? 굳


        // Log.d(TAG, "onCreate: $stationName , $stationNodeNumber")

        binding.BusStationName.text = stationName
        binding.backbtn.setOnClickListener {
            onBackPressed()
        }
        busFavoriteGetAll()



        SetBusStationRecyclerView()
        savemystation()
//        FavoriteStation()
//        loadData()
    }

    private fun savemystation()=with(binding){

        countingstars.setOnClickListener {


            val stationName = intent.getStringExtra("stationName").toString()
            val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
            val stationNodeNode = intent.getStringExtra("stationnodenode").toString()

            //db저장가즈아 하면돼 ㅇㅋ?

            val hello = TestFavoriteModel(
                id = null,
                checkBoolean =null,
                stationnodenode=stationNodeNode,
                stationName = stationName,
                stationNodeNumber = stationNodeNumber
            )
            BUSFravoriteInsert(hello)
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

                    val hello =body.body.items.item
                    val hi = mutableListOf<Item>()
                    for(i in hello.indices){
                        val busNm:String
                        val waitbus:Int
                        val waittime:Int

                        busNm = hello.get(i).routeno!!
                        waitbus = hello.get(i).arrprevstationcnt!!
                        waittime = hello.get(i).arrtime!!

                        hi.add(Item(
                            busNm,waitbus,waittime
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


                    val ResultList = mutableListOf<Item>()

                    firstList.forEach {
                        val ARouteNo = it.routeno
                        val AWaitstation = it.arrprevstationcnt
                        val AWaitTime = it.arrtime


//            Log.d(TAG, "onCreate: $AWaittime")


                        secondList.forEach {
                            val BRouteNo = it.routeno
                            val BWaitstation = it.arrprevstationcnt
                            val BWaittime = it.arrtime

                            if(ARouteNo==BRouteNo){
                                if(AWaitstation!! > BWaitstation!!){
//                                    Log.d(TAG, "onCreate: $it")
                                    ResultList.add(Item(it.routeno,it.arrprevstationcnt,it.arrtime))

                                }else{
                                    ResultList.add(Item(ARouteNo,AWaitstation,AWaitTime))
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

    private fun BUSFravoriteInsert(busfavoriteEntity:TestFavoriteModel){


        var businsertTask = (object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {

                activitybusfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()

                val stationnameList = mutableListOf<String>()

                for(i in activitybusfavoriteEntity.indices){
                    val stationname = activitybusfavoriteEntity.get(i).stationName
                    stationnameList.add(stationname)
                }

                if(binding.BusStationName.text !in stationnameList){
                    busFavoriteDB.busFavoriteDAO().busFavoriteInsert(busfavoriteEntity)
                }


            }



            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val stationnameList = mutableListOf<String>()

                for(i in activitybusfavoriteEntity.indices){
                    val stationname = activitybusfavoriteEntity.get(i).stationName
                    stationnameList.add(stationname)
                }

                if(binding.BusStationName.text in stationnameList){
                    Toast.makeText(this@DeepStationInfoActivity,"이미 즐겨찾기에 추가된 정류장입니다!",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@DeepStationInfoActivity,"즐겨찾기에 추가 되었습니다!",Toast.LENGTH_SHORT).show()
                    binding.countingstars.setImageResource(R.drawable.shinigstar)
                }

            }
        }).execute()
    }

    private fun busFavoriteGetAll(){
        val busGetAllTask = (object: AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                activitybusfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val stationnameList = mutableListOf<String>()

                for(i in activitybusfavoriteEntity.indices){
                    val stationname = activitybusfavoriteEntity.get(i).stationName
                    stationnameList.add(stationname)
                }

                if(binding.BusStationName.text in stationnameList){
                    binding.countingstars.setImageResource(R.drawable.shinigstar)
                }else{
                    binding.countingstars.setImageResource(R.drawable.star)
                }

            }

        }).execute()
    }


}