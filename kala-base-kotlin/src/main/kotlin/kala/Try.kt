@file:Suppress("NOTHING_TO_INLINE")

package kala

typealias Try<T> = asia.kala.Try<out T>
typealias Success<T> = asia.kala.Try.Success<out T>
typealias Failure = asia.kala.Try.Failure<Nothing>

inline fun <T> successOf(value: T): Success<T> = Try.success(value)
inline fun failureOf(throwable: Throwable): Failure = Try.failure(throwable)

inline fun <T> tryRun(op: () -> T): Try<T> {
    return try {
        successOf(op())
    } catch (throwable: Throwable) {
        failureOf(throwable)
    }
}


