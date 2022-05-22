package com.example.yourjourneyhelper.viewmodel

import android.app.Application
import android.graphics.Color
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.yourjourneyhelper.data.models.Means
import com.example.yourjourneyhelper.data.models.Tickets

class SharedViewModel(application: Application):AndroidViewModel(application) {

    val emptyDataBase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(tickets: List<Tickets>){
        emptyDataBase.value = tickets.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
            when(position){
                0 ->{
                    (parent!!.getChildAt(0) as TextView).setTextColor(Color.RED)
                }
                1 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLUE)
                }
                2 ->{
                    (parent!!.getChildAt(0) as TextView).setTextColor(Color.MAGENTA)
                }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }

    fun verifyDataFromUser(titile: String, means: String, prices: String): Boolean{

        return !(titile.isEmpty() || prices.isEmpty() || means.isEmpty())
    }

    fun parseMeans(means: String): Means{
        return when(means){
            "HighSpeedRailway" ->{
                Means.HighSpeedRailway
            }
            "Airplane" ->{
                Means.Airplane
            }
            else ->{
                Means.Train
            }
        }
    }


}