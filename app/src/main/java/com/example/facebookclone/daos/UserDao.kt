package com.example.facebookclone.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.facebookclone.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User) : Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from tbl_user where email = :email")
    suspend fun getUserByEmail(email : String) : User?
}