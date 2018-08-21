package com.example.nemanjatrajkovic.enumerator;

import android.os.Handler;
import android.widget.TextView;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.nemanjatrajkovic.enumerator.StringConstantsEnum.First;

public class EnumeratorTest {

    private static StringConstantsEnum[] enumValues = StringConstantsEnum.values();

    private TextView result;
    private Thread testThread = null;
    private  ReentrantLock lock = new ReentrantLock();
    Handler handler = new Handler();
    private  TimeTracker timeTracker = new TimeTracker();

    private Sample sample;

    private Runnable sampleTest = () -> {
        sampleEnumTest(sample.getValue());
        handler.post(()->{
            lock.unlock();
            result.setText(timeTracker.duration()+"");
        });
    };

    public EnumeratorTest(Sample sample) {
        this.sample = sample;
    }

    public void runSample(Handler handler, TextView result) {
        if (lock.getHoldCount() > 0) return;
        lock.lock();
        this.handler = handler;
        this.result = result;
        testThread = new Thread(sampleTest, getClass().getSimpleName()+"_runsSample");
        testThread.start();
    }


    private void sampleEnumTest(int sample) {
        timeTracker.start();
        StringConstantsEnum enumeration = First;
        for (int i = 0; i < sample; i++) {
            enumeration = enumerateAndGet();
        }
        timeTracker.end();
        System.out.println(enumeration.value);
    }

    private StringConstantsEnum enumerateAndGet() {
        StringConstantsEnum enumeration = First;
        for (StringConstantsEnum constant : enumValues) {
            switch (constant) {
                case First:
                    enumeration = constant;
                    break;
                case Second:
                    enumeration = constant;
                    break;
                case Third:
                    enumeration = constant;
                    break;
                case Forth:
                    enumeration = constant;
                    break;
                case Fifth:
                    enumeration = constant;
                    break;
                default:
                    enumeration = First;
            }
        }
        return enumeration;
    }
}
