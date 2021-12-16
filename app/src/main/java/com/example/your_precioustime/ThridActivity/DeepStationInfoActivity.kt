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


    lateinit var fuckyou : TestFavoriteModel

    private  var checkBoolean=true
    //일단주석이다ㄴㄴㄴㄴ


    private val retrofitInterface: Retrofit_InterFace = Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepStationbinding = ActivityDeepStationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stationName = intent.getStringExtra("stationName").toString()
        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
        //야 만약에 이페이지에서 별을 누르면 stationName, stationNodeNumber를 저장하면 되잖아 그리고 헷갈림을 방지하기위해서 일단 별모양 대신에 즐겨찾기 버튼이라거 하자 ㅇㅋ? 굳


//        Log.d(TAG, "onCreate: $stationName , $stationNodeNumber")

        binding.BusStationName.text = stationName
        binding.backbtn.setOnClickListener {
            onBackPressed()
        }



        binding.logBtn.setOnClickListener {
            busFavoriteGetAll()
        }

        SetBusStationRecyclerView()
        savemystation()
//        FavoriteStation()
        //주석가즈아
//        loadData()


    }

    private fun savemystation()=with(binding){

        countingstars.setOnClickListener {
            val stationName = intent.getStringExtra("stationName").toString()
            val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
            val stationNodeNode = intent.getStringExtra("stationnodenode").toString()

            Toast.makeText(this@DeepStationInfoActivity,"정류소이름 : $stationName \n 노드넘버 : $stationNodeNumber",Toast.LENGTH_SHORT).show()
            //db저장가즈아 하면돼 ㅇㅋ?

            val hello = TestFavoriteModel(id = null,
                checkBoolean =null,
                stationnodenode=stationNodeNode,
                stationName = stationName,
                stationNodeNumber = stationNodeNumber
            )


            BUSFravoriteInsert(hello)





        }
    }

    private fun FavoriteStation()=with(binding) {


        countingstars.setOnClickListener {
            saveData(checkBoolean)
            if(checkBoolean==true){
//                getSharedPrefrence()
                countingstars.setImageResource(R.drawable.shinigstar)
                Toast.makeText(this@DeepStationInfoActivity,"즐겨찾기에 저장되었습니다.",Toast.LENGTH_SHORT).show()
                checkBoolean=false
            }

        }

    }

    private fun saveData(checkBoolean:Boolean)=with(binding) {
        val pre = getSharedPreferences("test",0)
        val edit = pre.edit()
        edit.putBoolean("hellomyboolean",checkBoolean)
        edit.commit()
    }

    private fun loadData()= with(binding){
        val pre = getSharedPreferences("test",0)
        val mytext = pre.getBoolean("hellomyboolean",false)
        Toast.makeText(this@DeepStationInfoActivity,"hi : $mytext", Toast.LENGTH_SHORT).show()
        if(mytext == true){
            countingstars.setImageResource(R.drawable.shinigstar)
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

    private fun BUSFravoriteInsert(busfavoriteEntity:TestFavoriteModel){

        busFavoriteDB = BusFavroiteDataBase.getinstance(App.instance)!!

        var businsertTask = (object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {

                busFavoriteDB.busFavoriteDAO().busFavoriteInsert(busfavoriteEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                activitybusfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()
//                busFavoriteGetAll()



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
                Log.d(TAG, "여기에있는거확인하는거여ㅡㅡ: $activitybusfavoriteEntity")

            }

        }).execute()
    }


}