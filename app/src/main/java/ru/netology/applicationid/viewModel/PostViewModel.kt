package ru.netology.applicationid.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.applicationid.data.PostRepository
import ru.netology.applicationid.data.impl.InMemoryPostRepository

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()

    val data = repository.get()
    fun onLikeClicked() = repository.like()
    fun onShareCount() = repository.share()

}