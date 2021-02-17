package br.com.passoplantao.core.result

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: Error) : Result<Nothing>()

    fun <C> transform(transformation: (R) -> C): Result<C> {
        return when (this) {
            is Success -> Success(
                transformation(data)
            )
            is Failure -> Failure(
                error
            )
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$error]"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null