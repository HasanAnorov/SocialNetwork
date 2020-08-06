package com.example.socialnetwork.post

data class Post (
    var username:String ="",
    var theme:String="",
    var like:Int=0,
    var dislike:Int=0,
    var text:String="",
    var userId :String=""
)