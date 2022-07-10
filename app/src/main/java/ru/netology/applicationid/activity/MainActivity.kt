package ru.netology.applicationid.activity


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.netology.applicationid.databinding.ActivityMainBinding
import ru.netology.applicationid.viewModel.PostViewModel
import androidx.activity.viewModels
import ru.netology.applicationid.R
import ru.netology.applicationid.adapter.PostsAdapter
import ru.netology.applicationid.util.hideKeyboard
import ru.netology.applicationid.util.showKeyboard

class MainActivity : AppCompatActivity() {

    /*  override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)


          val binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)


          val viewModel: PostViewModel by viewModels()

          val adapter = PostsAdapter(viewModel)

          binding.postsRecyclerView.adapter = adapter
          viewModel.data.observe(this) { posts ->
              adapter.submitList(posts)


          }
          binding.buttonCancel.setOnClickListener {
              viewModel.onButtonCancelClicked()
          }

          binding.saveButton.setOnClickListener {
              with(binding.contentEdit) {
                  val content = text.toString()
                  viewModel.onButtonSaveClicked(content)
              }
          }

          viewModel.currentPost.observe(this) { currentPost ->
              with(binding) {
                  val content = currentPost?.content
                  contentEdit.setText(content)
                  previousText.hint = content
                  if (content != null) {
                      contentEdit.requestFocus()
                      contentEdit.showKeyboard()
                      groupEdit.visibility = View.VISIBLE
                  } else {
                      contentEdit.clearFocus()
                      contentEdit.hideKeyboard()
                      groupEdit.visibility = View.GONE


                  }


              }

          }
      }*/
    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter(viewModel)

        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            viewModel.onAddClicked()
        }

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

        val postContentActivityLauncher = registerForActivityResult(
            PostContentActivity.ResultContract
        ) { postContent ->
            postContent ?: return@registerForActivityResult
            viewModel.onButtonSaveClicked(postContent)
        }

        viewModel.navigateToPostContentScreenEvent.observe(this) {
            postContentActivityLauncher.launch(viewModel.currentPost.value?.content)
        }

    }

}
















