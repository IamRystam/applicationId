package ru.netology.applicationid


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.applicationid.databinding.ActivityMainBinding
import ru.netology.applicationid.viewModel.PostViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

            with(binding) {

                author.text = post.author
                published.text = post.published
                content.text = post.content
                likes.setImageResource(if (post.LikedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
                shareSum.text = formatNumber(post.shareCount )

                likesSum.text = formatNumber(post.likeCount)

                if (post.LikedByMe) {
                    post.likeCount + 1
                    likesSum.text = formatNumber(post.likeCount + 1 )
                } else {
                    post.likeCount - 1
                    likesSum.text = formatNumber(post.likeCount)
                }
            }

        }

        binding.likes.setOnClickListener {
            viewModel.onLikeClicked()


            binding.share.setOnClickListener {
                viewModel.onShareCount()


            }

        }


    }


    fun formatNumber(count: Int): String {

        return when (count) {
            in 0..999 -> {
                return count.toString()
            }
            in 1000..1099 -> {
                return (count / 1000).toString() + "K"
            }
            in 1100..9999 -> {
                return BigDecimal(count / 1000.0).setScale(1, RoundingMode.HALF_EVEN)
                    .toString() + "K"
            }
            in 10000..10099 -> {
                return (count / 1000).toString() + "K"
            }
            in 10100..99999 -> {
                BigDecimal(count / 1000.0).setScale(1, RoundingMode.HALF_EVEN)
                    .toString() + "K"
            }
            in 100000..100999 -> {
                return (count / 1000).toString() + "K"
            }
            in 110000..999999 -> {
                return BigDecimal(count / 1000.0).setScale(1, RoundingMode.HALF_EVEN)
                    .toString() + "K"
            }
            in 1000000..1099999 -> {
                return (count / 1000000).toString() + "M"
            }
            in 1100000..9999999 -> {
                return BigDecimal(count / 1000.0).setScale(1, RoundingMode.HALF_EVEN)
                    .toString() + "M"
            }
            else -> "error"
        }


    }

}







