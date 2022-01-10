package com.example.your_precioustime.SecondActivity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.R
import com.example.your_precioustime.SecondActivity.Busfragment.BusFragment
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Activity
import com.example.your_precioustime.SecondActivity.DB.BusFavroiteDataBase
import com.example.your_precioustime.SecondActivity.DB.OnDeleteInterFace
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel
import com.example.your_precioustime.SecondActivity.FavoriteFragment.FavoriteAdapter
import com.example.your_precioustime.SecondActivity.FavoriteFragment.FavroiteFragment
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayAdapter
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayFragment
import com.example.your_precioustime.Util
import com.example.your_precioustime.databinding.ActivitySecondBinding
import com.example.your_precioustime.databinding.FavoritelistFragmentBinding

@SuppressLint("StaticFieldLeak")
class SecondActivity : AppCompatActivity(), OnDeleteInterFace {

    private var secondBinding : ActivitySecondBinding? =null
    private val binding get() = secondBinding!!
    private var isFabOpen = false

    lateinit var busFavoriteDB : BusFavroiteDataBase
    lateinit var busfavoriteEntity: List<TestFavoriteModel>

    lateinit var favroiteAdapter: FavoriteAdapter
    lateinit var testAdapter: SubwayAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        busFavoriteDB = BusFavroiteDataBase.getinstance(App.instance)!!
//        ClickFragment()

        binding.floatingBtn.setOnClickListener {
            ToggleSet()
        }

        binding.BusfloatBtn.setOnClickListener {
            val intent = Intent(this,Bus_Activity::class.java)
            startActivity(intent)
        }


        binding.SubwayFloatBtn.setOnClickListener {
            val intent = Intent(this,SubwayFragment::class.java)
            startActivity(intent)
        }



        getAll()
        testAdapter = SubwayAdapter()



    }

    private fun ToggleSet(){
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

    private fun getAll(){

        val getAllTask = (@SuppressLint("StaticFieldLeak")
        object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                Log.d(Util.TAG, "onPostExecute: $busfavoriteEntity")
                setRecyclerViewfuck()

            }
        }).execute()
    }



    private fun setRecyclerViewfuck()=with(binding){

        favroiteAdapter = FavoriteAdapter(this@SecondActivity)

        FavoriteRecyclerView.apply {
            adapter = favroiteAdapter
            layoutManager= LinearLayoutManager(context)
            favroiteAdapter.submitList(busfavoriteEntity)

        }

    }


    private fun ondeleteList(testFavoriteModel: TestFavoriteModel){

        val deleteTask = (
        object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busFavoriteDB.busFavoriteDAO().busFavoriteDelete(testFavoriteModel)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAll()
            }

        }).execute()
    }

    override fun onDeleteFavroitelist(testFavoriteModel: TestFavoriteModel) {
        ondeleteList(testFavoriteModel)
    }



//    private fun ClickFragment() {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.SecondFragment, BusFragment())
//            .commit()
//
//        binding.BusInfoBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.SecondFragment, BusFragment())
//                .commit()
//        }
//
//        binding.SubwayinfoBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.SecondFragment , SubwayFragment())
//                .commit()
//        }
//
//
//        binding.FavoriteInfoBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.SecondFragment, FavroiteFragment())
//                .commit()
//        }
//
//    }
}