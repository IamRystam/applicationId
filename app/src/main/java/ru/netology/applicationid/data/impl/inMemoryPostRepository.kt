package ru.netology.applicationid.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.applicationid.Post
import ru.netology.applicationid.data.PostRepository

class InMemoryPostRepository : PostRepository {

    private var nextId = GENERATED_POSTS_AMOUNT.toLong()

    private var posts
        get() = checkNotNull(data.value)
        set(value) {
            data.value = value
        }

    override val data: MutableLiveData<List<Post>>

    init {

        val initialPosts = List(GENERATED_POSTS_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Rustam",
                content = "Контент поста №${index + 1}",
                published = "08.06.22",
                shareCount = 0,
                likeCount = 0,
                likedByMe = false
            )
        }

        data = MutableLiveData(initialPosts)
    }


    override fun like(postId: Long) {
        posts = posts.map { post ->
            if (post.id == postId) post.copy(
                likedByMe = !post.likedByMe,
                likeCount = post.likeCount + if (!post.likedByMe) +1 else -1
            )
            else post
        }


    }


    override fun share(postId: Long) {
        posts = posts.map { post ->
            if (post.id == postId) post.copy(shareCount = post.shareCount + 1)
            else post

        }

    }

    override fun delete(postId: Long) {
        data.value = posts.filterNot {it.id == postId }

    }
    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        data.value = listOf(
            post.copy(id = ++nextId)
        ) + posts
    }

    private fun update(post: Post) {
        data.value = posts.map {
            if (it.id == post.id) post else it
        }
    }

    private companion object {
        const val GENERATED_POSTS_AMOUNT = 15
    }

}

