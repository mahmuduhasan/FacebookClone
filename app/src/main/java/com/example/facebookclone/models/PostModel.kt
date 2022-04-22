package com.example.facebookclone.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.facebookclone.db.FacebookCloneDB
import com.example.facebookclone.entities.Post
import com.example.facebookclone.repositories.PostRepository
import kotlinx.coroutines.launch

class PostModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: PostRepository
    init {
        val postDao = FacebookCloneDB.getDB(application).getPostDao()
        repository = PostRepository(postDao)
    }

    fun insertPost(post: Post){
        viewModelScope.launch {
            repository.insertPost(post)
        }
    }

    fun updatePost(post: Post){
        viewModelScope.launch {
            repository.updatePost(post)
        }
    }

    fun deletePost(post: Post){
        viewModelScope.launch {
            repository.deletePost(post)
        }
    }

    fun getAllPost() : LiveData<List<Post>> = repository.getAllPost()

    fun getAllPostByUserId(userId : Long) : LiveData<List<Post>> = repository.getAllPostByUserId(userId)
}