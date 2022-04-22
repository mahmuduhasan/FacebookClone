package com.example.facebookclone.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    @ColumnInfo(name = "first_name")
    val firstName : String,
    @ColumnInfo(name = "last_name")
    val lastName : String,
    val email : String,
    val gender : String,
    val birthday : String,
    @ColumnInfo(name = "created_date_time")
    val createdDateTime : String,
    val password : String,
)
