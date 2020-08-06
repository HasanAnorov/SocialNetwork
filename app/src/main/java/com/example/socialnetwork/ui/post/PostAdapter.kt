package com.example.socialnetwork.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.R
import com.example.socialnetwork.post.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter:RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun populateModel(model: Post){
        itemView.tvTitle.text=model.theme
            itemView.tvSecondary.text=model.username
            itemView.tvPostText.text=model.text
        }
    }

    var models:List<Post> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_post,null,false)
    return PostViewHolder(view)
    }

    override fun getItemCount(): Int=models.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

}