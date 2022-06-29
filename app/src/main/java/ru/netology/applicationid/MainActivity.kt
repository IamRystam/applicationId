package ru.netology.applicationid


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.netology.applicationid.databinding.ActivityMainBinding
import ru.netology.applicationid.viewModel.PostViewModel
import androidx.activity.viewModels
import ru.netology.applicationid.adapter.PostsAdapter
import ru.netology.applicationid.util.hideKeyboard
import ru.netology.applicationid.util.showKeyboard

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
    }
}















