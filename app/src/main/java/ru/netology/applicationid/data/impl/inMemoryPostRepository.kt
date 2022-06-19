package ru.netology.applicationid.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.applicationid.Post
import ru.netology.applicationid.data.PostRepository

class InMemoryPostRepository : PostRepository {

    private var post = Post(

        id = 0L,
        author = "Rustam",
        content = "Events",
        published = "08.06.22",
        shareCount = 0,
        likeCount = 0,
        likedByMe = false

    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data


    override fun like() {
        post = post.copy(
            likedByMe = !post.likedByMe,
            likeCount = post.likeCount + if (!post.likedByMe) +1 else -1
        )

        data.value = post

    }


    override fun share() {
        post = post.copy(shareCount = post.shareCount + 1)
        data.value = post
    }
}





