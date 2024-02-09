package com.root14.passwordgenerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.root14.passwordgenerator.PasswordGeneratorBuilder
import com.root14.passwordgenerator.util.ClipBoardUtil
import com.root14.passwordgenerator.view.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val clipBoardUtil: ClipBoardUtil) : ViewModel() {


    private val _generatedPassword = MutableLiveData<String>()
    val generatedPassword: LiveData<String>
        get() = _generatedPassword


    fun generatePassword(builder: PasswordGeneratorBuilder): LiveData<String> {
        _generatedPassword.value = builder.build().generatePassword()
        return generatedPassword
    }

    private val _isCopySuccess = MutableLiveData<Boolean>()
    val isCopySuccess: LiveData<Boolean>
        get() = _isCopySuccess

    fun copy2ClipBoard(data: String): LiveData<Boolean> {
        when (clipBoardUtil.copy(data)) {
            is Result.Success -> _isCopySuccess.value = true

            is Result.Error -> _isCopySuccess.value = false
        }
        return isCopySuccess
    }

}