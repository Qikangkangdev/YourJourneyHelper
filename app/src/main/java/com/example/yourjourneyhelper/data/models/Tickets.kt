package com.example.yourjourneyhelper.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="tickets_table")
data class Tickets(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var price: Double,
    var means: Means,
    var hours: String,
    var start: String
):Parcelable