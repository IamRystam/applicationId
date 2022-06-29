package ru.netology.applicationid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.applicationid.Post
import ru.netology.applicationid.adapter.PostInteractionListener
import ru.netology.applicationid.data.PostRepository
import ru.netology.applicationid.data.impl.InMemoryPostRepository

class PostViewModel : ViewModel(), PostInteractionListener {
    private val repository: PostRepository = InMemoryPostRepository()

    val data get() = repository.data

    val currentPost = MutableLiveData<Post?>(null)

    fun onButtonSaveClicked(content: String) {
        if (content.isBlank()) return

        val post = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Me",
            content = content,
            published = "today",
            likeCount = 0,
            shareCount = 0
        )
        repository.save(post)
        currentPost.value = null
    }


    override fun onLikeClicked(post: Post) = repository.like(post.id)

    override fun onShareClicked(post: Post) = repository.share(post.id)

    override fun onRemoveClicked(post: Post) = repository.delete(post.id)

    override fun onButtonEditClicked(post: Post) {
        currentPost.value = post
    }

    override fun onButtonCancelClicked() {
        currentPost.value = null
    }
}

//fun onDeleteClicked(post: Post)= repository.delete(post.id)


