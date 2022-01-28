package com.example.your_precioustime.ObjectManager

import android.content.Context
import android.content.SharedPreferences
import com.example.your_precioustime.App

class citycodeSaveClass(context: Context) {

    companion object{
        val citycodeSaveClass = citycodeSaveClass(App.instance)
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun Savecitycode(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun Loadcitycode(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }



}