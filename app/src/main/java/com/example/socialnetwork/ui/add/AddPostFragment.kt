package com.example.socialnetwork.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.socialnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_post.*

private val mAuth=FirebaseAuth.getInstance()
private val db=FirebaseFirestore.getInstance()

class PostFragment:Fragment(R.layout.fragment_add_post) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSend.setOnClickListener {
            setLoading(true)
            if(!etPost.text.isNullOrEmpty()){
                val map:MutableMap <String,Any> = mutableMapOf()
                map["userId"]= mAuth.currentUser?.uid.toString()
                map["like"]=0
                map["comment"]= arrayListOf<String> ()
                map["text"] = etPost.text.toString()
                db.collection("posts").document().set(map)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(),"Your post is published",Toast.LENGTH_SHORT).show()
                        setLoading(false)
                    }
                    .addOnFailureListener {e->
                        Toast.makeText(requireContext(),e.localizedMessage!!,Toast.LENGTH_LONG).show()
                        setLoading(false)
                    }
            }
        }
    }
    private fun setLoading(isLoading:Boolean){
        progressBar.visibility=if (isLoading) View.VISIBLE else View.GONE
        etPost.isEnabled=!isLoading
        btnSend.isEnabled=!isLoading
    }
}