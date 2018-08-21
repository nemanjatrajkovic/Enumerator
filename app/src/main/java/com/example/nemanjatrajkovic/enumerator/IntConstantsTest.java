package com.example.nemanjatrajkovic.enumerator;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.Iterator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IntConstantsTest implements Iterable<Integer> {

    public static final int INT_ONE = 0;
    public static final int INT_TWO = 1;
    public static final int INT_THREE = 2;
    public static final int INT_FOUR = 3;
    public static final int INT_FIVE = 4;

    private static final int[] constants = new int[]{
            INT_ONE,
            INT_TWO,
            INT_THREE,
            INT_FOUR,
            INT_FIVE
    };
    private Sample sample;

    private Runnable sampleTest = () -> {
        sampleConstantsTest(sample.getValue());
    };
    private TextView result;
    private Thread testThread = null;
    private ReentrantLock lock = new ReentrantLock();
//    private Semaphore lock = new Semaphore(1);
    Handler handler;
    private TimeTracker timeTracker = new TimeTracker();

    public IntConstantsTest(Sample sample) {
        this.sample = sample;
    }

    private void sampleConstantsTest(int sample) {
        timeTracker.start();
        int intConstant = INT_ONE;
        for (int i = 0; i < sample; i++) {
            intConstant = switchAndGet();
        }
        timeTracker.end();
        System.out.println(intConstant);
        handler.post(() -> {
            lock.unlock();
            result.setText(timeTracker.duration() + "");
        });
        System.out.printf("start[%s]\nend[%s]\nduration[%s]",
                timeTracker.getStart(), timeTracker.getEnd(), timeTracker.duration());
    }

    public void runSample(Handler handler, TextView result) {
        if (lock.getHoldCount() > 0 ) return;

        this.handler = handler;
        this.result = result;
//        try {
            lock.lock();
            testThread = new Thread(sampleTest, getClass().getSimpleName() + "_runsSample");
            testThread.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private int switchAndGet() {
        int intConstantValue = INT_ONE;
        for (int constant : constants) {
            switch (constant) {
                case INT_ONE:
                    intConstantValue = INT_ONE;
                    break;
                case INT_TWO:
                    intConstantValue = INT_TWO;
                    break;
                case INT_THREE:
                    intConstantValue = INT_THREE;
                    break;
                case INT_FOUR:
                    intConstantValue = INT_FOUR;
                    break;
                case INT_FIVE:
                    intConstantValue = INT_FIVE;
                    break;
                default:
                    intConstantValue = INT_ONE;
            }
        }
        return intConstantValue;
    }

    @NonNull
    @Override
    public Iterator<Integer> iterator() {
        return new IntConstantsClassIterator();
    }

    public static class IntConstantsClassIterator implements Iterator<Integer> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < constants.length;
        }

        @Override
        public Integer next() {
            if (index >= constants.length) return null;
            switch (constants[index++]) {
                case INT_ONE:
                    return INT_ONE;
                case INT_TWO:
                    return INT_TWO;
                case INT_THREE:
                    return INT_THREE;
                case INT_FOUR:
                    return INT_FOUR;
                case INT_FIVE:
                    return INT_FIVE;
                default:
                    return null;
            }
        }
    }
}