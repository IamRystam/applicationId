package ru.netology.applicationid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.netology.applicationid.R
import ru.netology.applicationid.databinding.AppActivityBinding

class AppActivity:AppCompatActivity(R.layout.app_activity) {

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = AppActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            add(R.id.fragmentContainer, FeedFragment())
        }

    }*/
}