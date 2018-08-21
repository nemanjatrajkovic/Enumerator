package com.example.nemanjatrajkovic.enumerator;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringConstantsClass implements Iterable<String> {

    public static final String STRING_ONE = "FIRST_STRING_CONSTANT";
    public static final String STRING_TWO = "SECOND_STRING_CONSTANT";
    public static final String STRING_THREE = "THIRD_STRING_CONSTANT";
    public static final String STRING_FOUR = "FORTH_STRING_CONSTANT";
    public static final String STRING_FIVE = "FIFTH_STRING_CONSTANT";

    private static final String[] constants = new String[] {
            STRING_ONE,
            STRING_TWO,
            STRING_THREE,
            STRING_FOUR,
            STRING_FIVE
    };
    private Sample sample;

    private Runnable sampleTest = () -> {
        sampleConstantsTest(sample.getValue());
    };
    private TextView result;
    private Thread testThread = null;
    private ReentrantLock lock = new ReentrantLock();
    Handler handler = new Handler();
    private TimeTracker timeTracker = new TimeTracker();

    public StringConstantsClass(Sample sample) {
        this.sample = sample;
    }

    private void sampleConstantsTest(int sample) {
        timeTracker.start();
        String stringConstant = STRING_ONE;
        for (int i = 0; i < sample; i++) {
            stringConstant = switchAndGet();
        }
        timeTracker.end();
        System.out.println(stringConstant);
        handler.post(()-> {
            lock.unlock();
            result.setText(timeTracker.duration()+"");
        });
        System.out.printf("start[%s]\nend[%s]\nduration[%s]",
                timeTracker.getStart(), timeTracker.getEnd(), timeTracker.duration());
    }

    public void runSample(Handler handler, TextView result) {
        if (lock.getHoldCount() > 0) return;
        lock.lock();
        this.handler = handler;
        this.result = result;
        testThread = new Thread(sampleTest, getClass().getSimpleName()+"_runsSample");
        testThread.start();
    }

    private String switchAndGet() {
        String constantString = STRING_ONE;
        for (String constant : constants) {
            switch (constant) {
                case STRING_ONE:
                    constantString = STRING_ONE;
                    break;
                case STRING_TWO:
                    constantString = STRING_TWO;
                    break;
                case STRING_THREE:
                    constantString = STRING_THREE;
                    break;
                case STRING_FOUR:
                    constantString = STRING_FOUR;
                    break;
                case STRING_FIVE:
                    constantString = STRING_FIVE;
                    break;
                default:
                    constantString = STRING_ONE;
            }
        }
        return constantString;
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return new StringConstantsClassIterator();
    }

    public static class StringConstantsClassIterator implements Iterator<String> {

        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < constants.length;
        }

        @Override
        public String next() {
            if (index >= constants.length) return null;
            switch (constants[index++]) {
                case STRING_ONE:
                    return STRING_ONE;
                case STRING_TWO:
                    return STRING_TWO;
                case STRING_THREE:
                    return STRING_THREE;
                case STRING_FOUR:
                    return STRING_FOUR;
                case STRING_FIVE:
                    return STRING_FIVE;
                default:
                        return null;
            }
        }
    }
}
