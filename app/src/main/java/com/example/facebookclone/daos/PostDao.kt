package com.example.facebookclone.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.facebookclone.entities.Post

@Dao
interface PostDao {
    @Insert
    suspend fun insertPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)

    @Query("select * from tbl_post")
    fun getAllPost() : LiveData<List<Post>>

    @Query("select * from tbl_post where user_id = :userId")
    fun getPostByUserId(userId : Long) : LiveData<List<Post>>
}