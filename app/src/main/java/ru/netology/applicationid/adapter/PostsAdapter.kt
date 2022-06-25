package ru.netology.applicationid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.applicationid.Post
import ru.netology.applicationid.R
import ru.netology.applicationid.databinding.PostsListItemBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PostsAdapter(

    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit
) : ListAdapter<Post,PostsAdapter.ViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostsListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onLikeClicked, onShareClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }


    class ViewHolder(
        private val binding: PostsListItemBinding,
        private val onLikeClicked: (Post) -> Unit,
        private val onShareClicked: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {




        fun bind(post: Post) = with(binding) {

            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
            shareSum.text = formatNumber(post.shareCount)

            likesSum.text = formatNumber(post.likeCount)


            likes.setOnClickListener {
                onLikeClicked(post)
            }
            share.setOnClickListener {
                onShareClicked(post)
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
    private object DiffCallback:DiffUtil.ItemCallback<Post>(){

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id==newItem.id


        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem==newItem


    }
}