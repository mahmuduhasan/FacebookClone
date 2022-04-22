package com.example.facebookclone.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_post")
data class Post(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "post_id")
    val postId : Long = 0,
    @ColumnInfo(name = "user_id")
    val userId : Long = System.currentTimeMillis(),
    val post : String,
    @ColumnInfo(name = "created_date_time")
    val createdDateTime : String,
)
