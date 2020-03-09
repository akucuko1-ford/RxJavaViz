package com.example.rxjavaviz

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

private var output = ""
private var mapOutput = mutableListOf<String>()

fun <T, R> animate(
    source: Observable<T>,
    function: AnimatedObservable<T>.() -> Observable<R>
): Observable<R> =
    function.invoke(AnimatedObservable(source))

fun <T, R> AnimatedObservable<T>.map(transformer: (T) -> R): AnimatedObservable<R> =
    AnimatedObservable(
        source.delay(1500, TimeUnit.MILLISECONDS)
            .map(transformer)
            .doOnNext {
                output = "$it"
                print("\r$output")
//                output += "$output -- $it "
//                mapOutput.add(mapOutput.size-1, it.toString())
            })

fun <T> AnimatedObservable<T>.flatMap(transformer: (T) -> Unit): AnimatedObservable<T> {
    output = output + "\nasds"
    return this
}

fun <T> AnimatedObservable<T>.delay(): AnimatedObservable<T> =
    AnimatedObservable(source.delay(300, TimeUnit.MILLISECONDS))

fun <T> AnimatedObservable<T>.subscribe(): Observable<T> =
    source.doOnNext { print("\r$output") }

fun <T, R> AnimatedObservable<T>.switchMap(transformer: (T) -> Observable<R>): AnimatedObservable<R> {
    var switchMapOutput = "\r\n"
    return AnimatedObservable(source.switchMap(transformer).doOnNext {
        switchMapOutput += "$switchMapOutput -- $it "
        print(switchMapOutput)
    })
}


class AnimatedObservable<T>(val source: Observable<T>)

fun <T> Observable<T>.startAnimate(): T = blockingLast()