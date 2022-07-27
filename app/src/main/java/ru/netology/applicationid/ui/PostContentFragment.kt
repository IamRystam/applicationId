package ru.netology.applicationid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

import ru.netology.applicationid.databinding.PostContentFragmentBinding


class PostContentFragment() : Fragment() {

    private val  initialContent
        get() = requireArguments().getString(INITIAL_CONTENT_ARGS_KEY)






    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PostContentFragmentBinding.inflate(layoutInflater,container,false).also  { binding ->
        binding.edit.setText(initialContent)
        binding.edit.requestFocus()
        binding.ok.setOnClickListener {
            onOkButtonClick(binding)
        }


    }.root

    private fun onOkButtonClick(binding: PostContentFragmentBinding) {

        val text = binding.edit.text
        if (!text.isNullOrBlank()) {
            val resultBundle = Bundle(1)
       resultBundle.putString(RESULT_KEY,text.toString())
            setFragmentResult(REQUEST_KEY,resultBundle)
        }
        findNavController().popBackStack()
    }



     companion object {
         private const val INITIAL_CONTENT_ARGS_KEY = "initialContent"
         const val RESULT_KEY = "postNewContent"
         const val REQUEST_KEY = "requestKey"

    fun create (initialContent: String?) = PostContentFragment().apply {
        arguments = createBundle(initialContent)
    }


    fun createBundle(initialContent: String?) = Bundle(1).apply {
        putString(INITIAL_CONTENT_ARGS_KEY, initialContent)
    }

}
}