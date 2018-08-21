package com.example.nemanjatrajkovic.enumerator;

import android.os.Handler;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimeTracker {

    private long start;
    private long end;

    Handler handler;
    Lock taskLock = new ReentrantLock();

    Runnable taskRunnable;
    private Runnable resultRunnable;
    Runnable workerRunnable = () -> {
        start();
        taskRunnable.run();
        end();
        handler.post(resultRunnable);
    };

    public TimeTracker() {}

    public TimeTracker(Handler handler, Runnable taskRunnable, Runnable resultRunnable) {
        this.handler = handler;
        this.taskRunnable = taskRunnable;
    }

    public void executeTask() {
        taskLock.lock();
        Thread taskThread = new Thread(workerRunnable, getClass().getSimpleName()+"_Test");
        taskThread.start();
        taskLock.unlock();
    }

    public TimeTracker setTask(Runnable taskRunnable) {
        this.taskRunnable = taskRunnable;
        return this;
    }

    public TimeTracker setHandler(Handler handler) {
        this.handler = handler;
        return this;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long duration() {
        return end - start;
    }
}
