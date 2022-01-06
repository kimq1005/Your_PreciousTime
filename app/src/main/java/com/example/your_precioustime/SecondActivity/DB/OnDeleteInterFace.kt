package com.example.your_precioustime.SecondActivity.DB

import com.example.your_precioustime.SecondActivity.DB.SubwayDB.SubwayEntity
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel

interface OnDeleteInterFace {
    fun onDeleteFavroitelist(testFavoriteModel: TestFavoriteModel)
}

interface onSaveInterFace{
    fun onSaveSubwayList(subwayEntity: SubwayEntity)
}