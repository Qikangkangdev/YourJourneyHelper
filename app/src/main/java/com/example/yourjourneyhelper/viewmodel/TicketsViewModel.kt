package com.example.yourjourneyhelper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.yourjourneyhelper.data.TicketsDatabase
import com.example.yourjourneyhelper.data.models.Tickets
import com.example.yourjourneyhelper.data.repository.TicketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TicketsViewModel (application: Application): AndroidViewModel(application){

    private val ticketsDao = TicketsDatabase.getDatabase(application).ticketsDao()
    private val repository: TicketsRepository
    val getAllData: LiveData<List<Tickets>>

    init {
        repository = TicketsRepository(ticketsDao)
        getAllData = repository.getAllData

    }

    fun insertData(tickets: Tickets){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(tickets)
        }
    }

    fun updateData(tickets: Tickets){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(tickets)
        }
    }

    fun deleteData(tickets: Tickets){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(tickets)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery:String): LiveData<List<Tickets>> {
        return repository.searchDatabase(searchQuery)
    }
}