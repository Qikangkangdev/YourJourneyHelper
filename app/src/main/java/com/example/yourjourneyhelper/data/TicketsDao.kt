package com.example.yourjourneyhelper.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yourjourneyhelper.data.models.Tickets

@Dao
interface TicketsDao{
    @Query("SELECT * FROM tickets_table ORDER BY ID ASC")
    fun getAllData(): LiveData<List<Tickets>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(tickets: Tickets)

    @Update
    suspend fun updateData(tickets: Tickets)

    @Delete
    suspend fun deleteData(tickets: Tickets)

    @Query("DELETE FROM tickets_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM tickets_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery:String):LiveData<List<Tickets>>

}
