package com.example.domain.datatype

sealed class Either<out E, out V> {

    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()

}
    fun <V> value(value: V): Either<Nothing, V> = Either.Value(value)
    fun <E> error(value: E): Either<E, Nothing> = Either.Error(value)

    inline fun <V> either(action: () -> V): Either<Throwable, V> =
        try {
            value(action())
        } catch (e: Throwable) {
            error(e)
        }

    inline infix fun <E, V, V2> Either<E, V>.map(f: (V) -> V2): Either<E, V2> = when (this) {
        is Either.Error -> this
        is Either.Value -> Either.Value(f(this.value))
    }

    inline infix fun <E, E2, V> Either<E, V>.mapError(f: (E) -> E2): Either<E2, V> = when (this) {
        is Either.Error -> Either.Error(f(this.error))
        is Either.Value -> this
    }

    inline fun <E, V, V2, V3> Either<E, V>.biMap(
        f1: (E) -> V2,
        f2: (V) -> V3
    ): Either<V2, V3> = when (this) {
        is Either.Error -> Either.Error(f1(this.error))
        is Either.Value -> Either.Value(f2(this.value))
    }

    inline infix fun <E, V, V2> Either<E, V>.flatMap(f: (V) -> Either<E, V2>): Either<E, V2> =
        when (this) {
            is Either.Error -> this
            is Either.Value -> f(this.value)
        }


    inline fun <E, V, A> Either<E, V>.fold(f1: (E) -> A, f2: (V) -> A): A = when (this) {
        is Either.Error -> f1(this.error)
        is Either.Value -> f2(this.value)
    }