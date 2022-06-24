package ru.netology.applicationid.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.applicationid.Post
import ru.netology.applicationid.data.PostRepository
import ru.netology.applicationid.data.impl.InMemoryPostRepository

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()

    val data get() = repository.data
    fun onLikeClicked(post: Post) = repository.like(post.id)
    fun onShareCount(post: Post) = repository.share(post.id)


}