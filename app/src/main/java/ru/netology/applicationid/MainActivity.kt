package ru.netology.applicationid


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.applicationid.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 0L,
            author = "Rustam",
            content = "Events",
            published = "08.06.22",
            shareCount = 0,
            likeCount = 0

        )
        binding.likes.setOnClickListener {
            post.LikedByMe = !post.LikedByMe
            val imageResId = if (post.LikedByMe) R.drawable.ic_like_24 else R.drawable.ic_liked_24
            binding.likes.setImageResource(imageResId)
            post.likeCount = if (imageResId == R.drawable.ic_like_24) 0 else post.likeCount + 1
            val count = binding.likesSum
            count.text = formatNumber(post.likeCount)

        }

        binding.share.setOnClickListener {
            post.shareCount = post.shareCount + 1
            val count = binding.shareSum
            count.text =formatNumber(post.shareCount)
        }



    }

    private fun formatNumber(count: Int): String {

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








