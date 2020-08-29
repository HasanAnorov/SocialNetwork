package com.example.socialnetwork.ui.post

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.R
import com.example.socialnetwork.post.Post
import com.example.socialnetwork.ui.profile.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter:RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

private var onItemClicked:(models:Post)->Unit = {}

    fun setOnItemClickListener(onItemCLicked:(model:Post)->Unit){
    this.onItemClicked=onItemCLicked
    }

    inner class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val mAuth= FirebaseAuth.getInstance()
        private val db= FirebaseFirestore.getInstance()

        fun populateModel(model: Post){
            db.collection("users").document(mAuth.currentUser?.uid!!.toString()).get().addOnSuccessListener {
                itemView.tvSecondary.text = it.get("username").toString()
            }
            itemView.tvTitle.text=model.theme
            itemView.tvPostText.text=model.text
            itemView.setOnClickListener {
                onItemClicked.invoke(model)
            }
        }
    }

    var models:List<Post> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)
    return PostViewHolder(view)
    }

    override fun getItemCount(): Int=models.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

}