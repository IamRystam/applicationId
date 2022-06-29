package ru.netology.applicationid.adapter

import ru.netology.applicationid.Post

interface PostInteractionListener {


    fun onLikeClicked(post: Post)
    fun onShareClicked(post: Post)
    fun onRemoveClicked(post: Post)
    fun onButtonEditClicked(post: Post)
    fun onButtonCancelClicked()

}