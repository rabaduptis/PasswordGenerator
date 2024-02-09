package com.root14.passwordgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.root14.passwordgenerator.util.ClipBoardUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val clipBoardUtil: ClipBoardUtil) : ViewModel() {

    private val _copy = MutableLiveData<Boolean>()
    val isCopyClicked: LiveData<Boolean>
        get() = _copy

    fun copy2ClipBoard(data: String) {


    }

}