package ru.netology.applicationid


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.applicationid.databinding.ActivityMainBinding
import ru.netology.applicationid.viewModel.PostViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import androidx.activity.viewModels
import ru.netology.applicationid.adapter.PostsAdapter
import ru.netology.applicationid.databinding.PostsListItemBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            onLikeClicked = { post ->
                viewModel.onLikeClicked(post)

            },
            onShareClicked = { post ->
                viewModel.onShareCount(post)
            }


        )
        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)


        }


    }


}



















