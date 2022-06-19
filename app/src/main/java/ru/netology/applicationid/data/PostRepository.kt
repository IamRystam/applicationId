package ru.netology.applicationid.data

import androidx.lifecycle.LiveData
import ru.netology.applicationid.Post

interface PostRepository {

    fun get(): LiveData<Post>

    fun like()

    fun share()


}