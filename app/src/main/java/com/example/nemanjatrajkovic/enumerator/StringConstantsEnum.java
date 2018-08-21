package com.example.nemanjatrajkovic.enumerator;

import android.os.Handler;
import android.widget.TextView;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum StringConstantsEnum {

    First("FIRST_STRING_CONSTANT"),
    Second("SECOND_STRING_CONSTANT"),
    Third("THIRD_STRING_CONSTANT"),
    Forth("FORTH_STRING_CONSTANT"),
    Fifth("FIFTH_STRING_CONSTANT");

    public final String value;

    StringConstantsEnum(String value) {
        this.value = value;
    }
}
