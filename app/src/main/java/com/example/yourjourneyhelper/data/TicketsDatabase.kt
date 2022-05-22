package com.example.yourjourneyhelper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yourjourneyhelper.data.models.Tickets

@Database(entities = [Tickets::class], version=1, exportSchema = false)
abstract class TicketsDatabase:RoomDatabase(){
    abstract fun ticketsDao(): TicketsDao

    companion object{
        @Volatile
        private var INSTANCE:TicketsDatabase?=null

        fun getDatabase(context: Context):TicketsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TicketsDatabase::class.java, "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}