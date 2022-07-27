package ru.netology.applicationid.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.applicationid.Post
import ru.netology.applicationid.adapter.PostInteractionListener
import ru.netology.applicationid.data.PostRepository
import ru.netology.applicationid.data.impl.FilePostRepository
import ru.netology.applicationid.data.impl.InMemoryPostRepository
import ru.netology.applicationid.util.SingleLiveEvent

class PostViewModel(
    application: Application
) : AndroidViewModel(application), PostInteractionListener {
    private val repository: PostRepository = FilePostRepository(application)

    val data get() = repository.data

    val sharePostContent = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<String>()
    val playVideoURL = SingleLiveEvent<String>()

    val currentPost = MutableLiveData<Post?>(null)

    fun onAddClicked() {
        navigateToPostContentScreenEvent.call()
    }

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

    override fun onShareClicked(post: Post) {
        sharePostContent.value = post.content
    }

    override fun onButtonPlayVideoClicked(post: Post) {
        playVideoURL.value = post.videoURL
    }

    override fun onRemoveClicked(post: Post) = repository.delete(post.id)

    override fun onButtonEditClicked(post: Post) {
        currentPost.value = post
        navigateToPostContentScreenEvent.value = post.content
    }

    // override fun onButtonCancelClicked() {
    // currentPost.value = null
    //}
}

//fun onDeleteClicked(post: Post)= repository.delete(post.id)


