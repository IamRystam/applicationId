package ru.netology.applicationid.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import ru.netology.applicationid.viewModel.PostViewModel
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.applicationid.R
import ru.netology.applicationid.adapter.PostsAdapter
import ru.netology.applicationid.databinding.FeedFragmentBinding

class FeedFragment : Fragment() {


    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






        viewModel.sharePostContent.observe(this) { postContent ->
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, postContent)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(
                intent, getString(R.string.chooser_share_post)
            )
            startActivity(shareIntent)
        }

        viewModel.playVideoURL.observe(this) { videoURL ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoURL))
            startActivity(intent)
        }
        setFragmentResultListener(
            requestKey = PostContentFragment.REQUEST_KEY
        ){requestKey, bundle ->
            if (requestKey!== PostContentFragment.REQUEST_KEY ) return@setFragmentResultListener
            val newPostContent = bundle.getString(
                PostContentFragment.RESULT_KEY
            )?: return@setFragmentResultListener
            viewModel.onButtonSaveClicked(newPostContent)
        }


        viewModel.navigateToPostContentScreenEvent.observe(this) { initialContent ->
            findNavController().navigate(
                R.id.toPostContentFragment,
                PostContentFragment.createBundle(initialContent)
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FeedFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
        val adapter = PostsAdapter(viewModel)

        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            viewModel.onAddClicked()
        }
    }.root

   companion object {
    const val TAG = "FeedFragment"
}
}
















