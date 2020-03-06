package com.example.rxjavaviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private var outputText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable.add(
            Observable.interval(0, 500, TimeUnit.MILLISECONDS)
                .map { "$outputText -- it" }
                .subscribe { print(it) }
        )
    }
}

fun main() = runBlocking {
    var outputText = "\r"

    Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
        .map { "$outputText -- it" }
        .doOnNext { outputText = it }
        .subscribe { print(it) }

    delay(10000)
}
