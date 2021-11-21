package com.example.your_precioustime.ThridActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
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

//        binding.fuckingbutton.setOnClickListener {
//            val hi = busEntity?.size
//            Log.d(TAG, "onCreate: $hi ")
//        }

//        val fubusid = intent.getStringExtra("busnum")
//        val fuwaitstaion = intent.getStringExtra("arriveStation")
//
//
//        binding.fuckBusnum.text = fubusid
//        binding.fuckWaitstation.text = fuwaitstaion

        binding.fuckingbutton.setOnClickListener {
//
//            val fuck = BUSEntity(
//                null,
//                fubusid,
//                fuwaitstaion,
//                null
//            )

            getAllBus()
//
//
//            businsert(fuck)
//            Toast.makeText(this,"저장완료다 이련아 $busEntity",Toast.LENGTH_SHORT).show()
//        }
//
//        binding.fuckingRecyclerbutton.setOnClickListener {

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