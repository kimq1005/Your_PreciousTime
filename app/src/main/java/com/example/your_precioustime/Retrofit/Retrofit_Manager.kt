package com.example.your_precioustime.Retrofit

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.*
import com.example.your_precioustime.Model.SubwayModel.SubwayModel
import com.example.your_precioustime.SecondActivity.BusItem
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayAdapter
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.google.gson.JsonElement
import com.tickaroo.tikxml.TikXml
import org.xmlpull.v1.builder.XmlElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.create
import java.lang.Exception

class Retrofit_Manager {
//    lateinit var upAdpater: UpAdpater

    companion object{
        val retrofitManager = Retrofit_Manager()
    }

    private var retrofitInterface = Retrofit_Client.getJsonClienet(Url.SEOUL_SUBWAY_MAIN_URL)
        .create(Retrofit_InterFace::class.java)



    fun getsubwayCall(statNm: String, mymodel:(MutableList<SubwayItem>)->Unit) {

        val call = retrofitInterface.SUBWAYGET(
            statnNm = statNm
        )

        call.enqueue(object : retrofit2.Callback<SubwayModel> {
            override fun onResponse(call: Call<SubwayModel>, response: Response<SubwayModel>) {
                val body = response.body()
                val subwaymodel = mutableListOf<SubwayItem>()
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

                    mymodel(subwaymodel)





                }

            }

            override fun onFailure(call: Call<SubwayModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })

    }
}