package com.example.rxjavaviz

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() = runBlocking {

//    Observable.animate(0, 1000, TimeUnit.MILLISECONDS)
//        .map ( { it.toString() }, 1)
//        .subscribe {  }

    animate(Observable.interval(0, 1000, TimeUnit.MILLISECONDS).take(10)) {
        map { it.toString() }
//        flatMap { Observable.interval(0, 1000, TimeUnit.MILLISECONDS) }
    }.blockingLast()

    delay(1)
}