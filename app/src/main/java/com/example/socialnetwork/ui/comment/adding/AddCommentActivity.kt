package com.example.socialnetwork.ui.comment.adding

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.socialnetwork.R
import com.example.socialnetwork.post.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_comment.*

class AddCommentActivity : AppCompatActivity() {

    private val db=FirebaseFirestore.getInstance()
    private val mAuth=FirebaseAuth.getInstance()
    private var postId=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)

        postId=intent.getStringExtra("PostId")?:"0"

        btnAddComment.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            if(!etComment.text.isNullOrEmpty()){
                db.collection("posts").document(postId).get().addOnSuccessListener {
                    if(it.exists()){
                        val post=it.toObject(Post::class.java)

                        var username=""

                            db.collection("posts").document(mAuth.currentUser!!.uid).get().addOnSuccessListener {user->
                                username= user.get("username").toString()

                                post?.comments?.add(mapOf("username" to username, "comment_text" to etComment.text.toString() ))
                                val newPost = mutableMapOf<String,Any?>()
                                newPost["id"] = post?.id
                                newPost["username"] = post?.username
                                newPost["theme"] = post?.theme
                                newPost["like"] = post?.like
                                newPost["dislike"] = post?.dislike
                                newPost["text"] = post?.text
                                newPost["userId"] = post?.userId
                                newPost["comments"] = post?.comments

                                db.collection("posts").document(postId).update(newPost).addOnSuccessListener {
                                    Toast.makeText(this,"Your comment added",Toast.LENGTH_SHORT).show()
                                }
                                    .addOnFailureListener {
                                        Toast.makeText(this,"Oops something gets wrong with you ...",Toast.LENGTH_LONG).show()
                                    }
                                    .addOnCompleteListener {
                                        progressBar.visibility=View.GONE
                                        finish()
                                    }
                                }
                            }
                        }
            }
        }
    }
}