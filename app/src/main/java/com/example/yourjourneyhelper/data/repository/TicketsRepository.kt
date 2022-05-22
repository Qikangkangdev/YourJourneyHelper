package com.example.yourjourneyhelper.data.repository

import androidx.lifecycle.LiveData
import com.example.yourjourneyhelper.data.TicketsDao
import com.example.yourjourneyhelper.data.models.Tickets

class TicketsRepository(private val ticketsDao: TicketsDao) {
    val getAllData:LiveData<List<Tickets>> = ticketsDao.getAllData()

    suspend fun insertData(tickets: Tickets){
        ticketsDao.insertData(tickets)
    }

    suspend fun updateData(tickets: Tickets){
        ticketsDao.updateData(tickets)
    }

    suspend fun deleteData(tickets: Tickets){
        ticketsDao.deleteData(tickets)
    }

    suspend fun deleteAll(){
        ticketsDao.deleteAll()
    }

    fun searchDatabase(searchQuery:String):LiveData<List<Tickets>>{
        return ticketsDao.searchDatabase(searchQuery)
    }
}