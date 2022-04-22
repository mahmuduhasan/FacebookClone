package com.example.facebookclone.repositories

import androidx.lifecycle.LiveData
import com.example.facebookclone.daos.PostDao
import com.example.facebookclone.entities.Post

class PostRepository(val postDao: PostDao) {
    suspend fun insertPost(post: Post){
        postDao.insertPost(post)
    }

    suspend fun updatePost(post: Post){
        postDao.updatePost(post)
    }

    suspend fun deletePost(post: Post){
        postDao.deletePost(post)
    }

    fun getAllPost() : LiveData<List<Post>> = postDao.getAllPost()

    fun getAllPostByUserId(userId : Long) : LiveData<List<Post>> = postDao.getPostByUserId(userId)
}