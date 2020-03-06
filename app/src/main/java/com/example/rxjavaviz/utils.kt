package com.example.rxjavaviz

import io.reactivex.Observable
import java.lang.Exception

private var output = "\r"

fun <T, R> animate(source: Observable<T>, function: FordMapper<T>.() -> Observable<R>): Observable<R> =
    function.invoke(FordMapper(source))

fun <T, R> FordMapper<T>.map(transformer: (T) -> R): Observable<R> =
    source.map(transformer).doOnNext {
        output += "$output -- $it "
        print(output)
    }

fun <T, R> FordMapper<T>.flatMap(transformer: (T) -> Observable<R>): Observable<R> {
    var flatMapOutput = "\r"
    return source.flatMap(transformer).doOnNext {
        flatMapOutput += "$flatMapOutput -- $it "
        print(flatMapOutput)
    }
}


class FordMapper<T> (val source: Observable<T>)