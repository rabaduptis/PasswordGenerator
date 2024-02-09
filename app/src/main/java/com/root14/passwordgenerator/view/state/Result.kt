package com.root14.passwordgenerator.view.state

sealed class Result<out T> {
    data class Success<T>(val data: T, val successMessage: String = "") : Result<T>()
    data class Error<T>(val exception: Exception, val errorMessage: String = "") : Result<T>()
}
