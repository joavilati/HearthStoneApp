package com.example.hearthstoneapp.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseUseCase<T>(
    private val job: Job = Job(),
    protected val liveData: MutableLiveData<T> = MutableLiveData()
) : UseCase<T> {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    abstract fun execute()

    protected fun launch(block: suspend () -> Unit) {
        coroutineScope.launch {
            block()
        }
    }

    override fun getLiveData(): LiveData<T> = liveData

    override fun cleanUp() {
        job.cancel()
    }
}
