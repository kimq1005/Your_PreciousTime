package com.example.your_precioustime.SecondActivity.DB

import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel

interface OnDeleteInterFace {
    fun onDeleteFavroitelist(testFavoriteModel: TestFavoriteModel)

}

interface OnSubwayListDeleteInterFace{
    fun onDeleteSubwayList(subwayNameEntity : SubwayNameEntity)
}