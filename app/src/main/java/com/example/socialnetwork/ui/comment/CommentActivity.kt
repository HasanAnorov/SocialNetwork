package com.example.socialnetwork.ui.comment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.socialnetwork.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {

    var adapter=CommentListAdapter()
    val db=FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        rvComment.adapter=adapter
        var id=""
        id=intent.getStringExtra("postId")?:""
        setComments(id)
    }


    private fun setComments(id:String){
    db.collection("posts").document(id).get()
        .addOnSuccessListener {
if(it.exists()){
    it.get("comment")?.let {comments->
        adapter.models=comments as List<Map<String,String>>
    }
}
            }
    }
}