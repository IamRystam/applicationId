package ru.netology.applicationid.data

import androidx.lifecycle.LiveData
import ru.netology.applicationid.Post

interface PostRepository {

    val data: LiveData<List<Post>>

    fun like(postId: Long)

    fun share(postId: Long)


}