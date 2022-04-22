package com.example.facebookclone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.facebookclone.daos.PostDao
import com.example.facebookclone.daos.UserDao
import com.example.facebookclone.entities.Post
import com.example.facebookclone.entities.User

@Database(entities = [User::class, Post::class], version = 1)
abstract class FacebookCloneDB : RoomDatabase() {

    abstract fun getPostDao() : PostDao
    abstract fun getUserDao() : UserDao

    companion object{
        private var db : FacebookCloneDB? = null
        fun getDB(context: Context) : FacebookCloneDB {
            if (db == null){
                db = Room.databaseBuilder(
                    context.applicationContext,
                    FacebookCloneDB::class.java,
                    "facebook_clone_DB"
                ).build()
                return db!!
            }
            return db!!
        }
    }
}