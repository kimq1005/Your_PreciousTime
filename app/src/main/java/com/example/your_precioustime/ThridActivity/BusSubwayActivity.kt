package com.example.your_precioustime.ThridActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.SecondActivity.DB.BUSDataBase
import com.example.your_precioustime.SecondActivity.DB.BUSEntity
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.ActivityBusSubwayBinding
@SuppressLint("StaticFieldLeak")

class BusSubwayActivity : AppCompatActivity() {
    private var busSubwayActivityBinding : ActivityBusSubwayBinding? =null
    private val binding get() = busSubwayActivityBinding!!
    private lateinit var busDataBase: BUSDataBase
    private lateinit var busCallAdpater: BusCallAdpater

    private var busEntity:List<BUSEntity>? = null

//    private var busEntity:List<BUSEntity>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        busSubwayActivityBinding = ActivityBusSubwayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        busDataBase = BUSDataBase.getinstance(this)!!

        val fuckGodt = mutableListOf<Item>()

        val fucklist1 = listOf(
            Item(routeno="92-1", arrprevstationcnt=9),
            Item(routeno="62-1", arrprevstationcnt=7),
            Item(routeno="99", arrprevstationcnt=3)
        )



        val fucklist2 = listOf(
            Item(routeno="92-1", arrprevstationcnt=12),
            Item(routeno="62-1", arrprevstationcnt=6),
            Item(routeno="99", arrprevstationcnt=23)
        )

        val kingfucklist = mutableListOf<Item>()

        fucklist1.forEach {
            val ARouteNo = it.routeno
            val AWaittime = it.arrprevstationcnt
            var found = false

//            Log.d(TAG, "onCreate: $AWaittime")

            
            fucklist2.forEach {
                val BRouteNo = it.routeno
                val BWaittime = it.arrprevstationcnt


                if(ARouteNo==BRouteNo){
                    if(AWaittime!! > BWaittime!!){
                        Log.d(TAG, "onCreate: $it")
                        fuckGodt.add(Item(it.routeno,it.arrprevstationcnt))

                    }else{
                        fuckGodt.add(Item(ARouteNo,AWaittime))
                    }

                    Log.d(TAG, "onCreate: $fuckGodt")
                }


            }


        }


//        for(i in 0..fucklist1.size-1){
//            Log.d(TAG, "onCreate: $i hi")
//
//        }






        binding.fuckingbutton.setOnClickListener {

        }








    }



//    private fun businsert(busEntity: BUSEntity){
//
//        busDataBase = BUSDataBase.getinstance(App.instance)!!
//        var businsertTask = (object : AsyncTask<Unit,Unit,Unit>(){
//            override fun doInBackground(vararg params: Unit?) {
//                busDataBase.busDAO().businsert(busEntity)
//            }
//
//            override fun onPostExecute(result: Unit?) {
//                super.onPostExecute(result)
//                busGetAll()
//            }
//        }).execute()
//
//    }

//    private fun busGetAll(){
//        val busGetAllTask = (object:AsyncTask<Unit,Unit,Unit>(){
//            override fun doInBackground(vararg params: Unit?) {
//                busEntity=busDataBase.busDAO().busgetAll()
//            }
//        }).execute()
//    }
//
//
//    @SuppressLint("StaticFieldLeak")
    private fun getAllBus(){
        val getAllTast = (object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busEntity = busDataBase.busDAO().busgetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView()
            }

        }).execute()
    }

    private fun setRecyclerView() {

        busCallAdpater = BusCallAdpater()
        busCallAdpater.submitList(busEntity)

        binding.busFVRecyclerView.apply {
            adapter = busCallAdpater
            layoutManager = GridLayoutManager(App.instance,2, GridLayoutManager.VERTICAL,false)

        }
    }
}