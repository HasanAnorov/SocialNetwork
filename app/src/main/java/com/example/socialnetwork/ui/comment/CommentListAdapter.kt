package com.example.socialnetwork.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.R
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentListAdapter:RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>() {

    var models:List<Map<String,String>> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun populateModel(comment:Map<String,String>){
            itemView.tvComment.text= comment["comment_text"]
            itemView.tvUsername.text= "@${comment["username"]}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent, false)
    return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
       holder.populateModel(models[position])
    }

    override fun getItemCount():Int = models.size

}