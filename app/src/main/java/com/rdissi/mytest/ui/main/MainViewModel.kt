package com.rdissi.mytest.ui.main

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun shareStory(context: Context) {
        Toast.makeText(context, "Not implemented !", Toast.LENGTH_SHORT).show()
    }
}
