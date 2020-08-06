package com.example.socialnetwork.ui.post

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.socialnetwork.R
import com.example.socialnetwork.post.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_get_post.*

class GetPostFragment:Fragment(R.layout.fragment_get_post) {

    private val db = FirebaseFirestore.getInstance()
    private var adapter=PostAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPost.adapter=adapter
        rvPost.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))

        getAllPost()

    }



    private fun getAllPost() {
        val result:MutableList<Post> = mutableListOf()
        db.collection("posts").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(requireContext(),error.message.toString(),Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            db.collection("posts").get().addOnSuccessListener {
                it.documents.forEach {doc ->
                    val model= doc.toObject(Post::class.java)
                    model?.let {
                        result.add(model)
                    }
                }
                adapter.models=result
                Log.d("malumotlar",result.toString())
            }
        }
    }

}