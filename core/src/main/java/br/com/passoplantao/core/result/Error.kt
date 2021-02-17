package br.com.passoplantao.core.result

sealed class Error {

    object TokenError : Error()

    object InvalidInputFormError : Error()

    object ItemNotFoundError : Error()

    object ServerInternError : Error()

    object DatabaseInternError : Error()

    data class ServerError<T>(val data: T) : Error()

    data class FeatureError<T>(val data: T) : Error()
}
