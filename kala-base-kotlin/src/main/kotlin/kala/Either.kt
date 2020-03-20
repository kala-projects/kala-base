@file:Suppress("NOTHING_TO_INLINE")

package kala

typealias Either<A, B> = asia.kala.control.Either<out A, out B>
typealias Left<T> = asia.kala.control.Either.Left<out T, Nothing>
typealias Right<T> = asia.kala.control.Either.Right<Nothing, out T>
typealias LeftProjection<A, B> = asia.kala.control.Either<out A, out B>.LeftProjection
typealias RightProjection<A, B> = asia.kala.control.Either<out A, out B>.RightProjection

inline fun <T> leftOf(value: T): Left<T> = asia.kala.control.Either.left(value)
inline fun <T> rightOf(value: T): Right<T> = asia.kala.control.Either.right(value)
