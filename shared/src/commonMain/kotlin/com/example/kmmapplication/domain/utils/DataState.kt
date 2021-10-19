package com.example.kmmapplication.domain.utils

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false
) {
    companion object {
        fun <T> error(message: String): DataState<T> {
            return DataState(message = message)
        }

        fun <T> data(message: String?, data: T? = null): DataState<T> {
            return DataState(message, data)
        }

        fun <T> loading(): DataState<T> {
            return DataState(isLoading = true)
        }
    }
}