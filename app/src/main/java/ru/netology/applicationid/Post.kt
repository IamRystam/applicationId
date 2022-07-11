package ru.netology.applicationid

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likedByMe: Boolean = false,
    var shareCount: Int,
    var likeCount: Int,
    val videoURL: String = ""
)

