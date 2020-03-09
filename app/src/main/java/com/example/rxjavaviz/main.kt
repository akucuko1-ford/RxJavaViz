package com.example.rxjavaviz

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() = runBlocking {

//    Observable.animate(0, 1000, TimeUnit.MILLISECONDS)
//        .map ( { it.toString() }, 1)
//        .subscribe {  }

    val name = "ardakucukoz"
    val array = mutableListOf("a", "r","d", "a","k", "u","c", "u","k", "o","z")

    animate(Observable.interval(0, 4000, TimeUnit.MILLISECONDS).take(10)) {
        this.map { it.toString() }
            .map { name.substring(0, it.toInt()+1) }
//            .map { array[it.toInt()] }
            .flatMap {  }
            .subscribe()
//            .switchMap { Observable.interval(200, TimeUnit.MILLISECONDS).take(5) }
//        flatMap { Observable.interval(0, 1000, TimeUnit.MILLISECONDS) }
    }.startAnimate()

    delay(1)
}