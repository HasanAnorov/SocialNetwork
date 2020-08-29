package com.example.socialnetwork.post

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class Post (
    var id:String="",
    var username:String ="",
    var theme:String="",
    var like:Int=0,
    var dislike:Int=0,
    var text:String="",
    var userId :String=""
){
    private val mAuth= FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


}