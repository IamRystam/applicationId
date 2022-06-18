package ru.netology.applicationid

data class Post (
    val id:Long,
    val author:String,
    val content:String,
    val published:String,
    var LikedByMe:Boolean,
    var shareCount:Int,
    var likeCount:Int
        )
