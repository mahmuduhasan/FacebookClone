package com.example.facebookclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookclone.databinding.PostViewBinding
import com.example.facebookclone.entities.Post

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffUtil()) {
    class PostViewHolder(val binding: PostViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun postBind(post: Post){
            binding.fbPost = post
        }
    }

    class PostDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.postBind(post)
    }
}