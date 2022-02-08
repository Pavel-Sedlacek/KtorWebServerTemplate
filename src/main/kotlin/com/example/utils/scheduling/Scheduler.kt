package com.example.utils.scheduling

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

open class Scheduler private constructor() {

    private val executors = Executors.newScheduledThreadPool(2)

    fun timeout(task: Task, now: Boolean) {
        executors.scheduleWithFixedDelay(
            task.action,
            if (now) 0 else task.timeoutMillis,
            task.timeoutMillis,
            TimeUnit.MILLISECONDS
        )
    }

    fun schedule(task: Task) {
        executors.schedule(task.action, task.timeoutMillis, TimeUnit.MILLISECONDS)
    }

    companion object : Scheduler()
}

data class Task(val timeoutMillis: Long, val action: Runnable)