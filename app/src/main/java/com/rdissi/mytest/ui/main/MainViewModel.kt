package com.rdissi.mytest.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun shareStory(context: Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "https://github.com/SamyAndroid/MyTest")
            putExtra(Intent.EXTRA_SUBJECT, "MyTest")
            putExtra(Intent.EXTRA_TITLE, "MyTest on GitHub")
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share My Test")
        context.startActivity(shareIntent)
    }
}
