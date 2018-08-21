package com.example.nemanjatrajkovic.enumerator;

import org.junit.jupiter.api.Test;

import static com.example.nemanjatrajkovic.enumerator.StringConstantsClass.STRING_FIVE;
import static com.example.nemanjatrajkovic.enumerator.StringConstantsClass.STRING_FOUR;
import static com.example.nemanjatrajkovic.enumerator.StringConstantsClass.STRING_ONE;
import static com.example.nemanjatrajkovic.enumerator.StringConstantsClass.STRING_THREE;
import static com.example.nemanjatrajkovic.enumerator.StringConstantsClass.STRING_TWO;

class StringConstantsClassTest {

    private static final String[] constants = new String[] {
                STRING_ONE,
                STRING_TWO,
                STRING_THREE,
                STRING_FOUR,
                STRING_FIVE
    };

    @Test void simpleConstantsTest() {
        String stringConstant = STRING_ONE;
        for (int i = 0; i < 1_000; i++) {
            stringConstant = switchAndGet();
        }
        System.out.println(stringConstant);
    }

    @Test void mediumConstantsTest() {
        String stringConstant = STRING_ONE;
        for (int i = 0; i < 100_000; i++) {
            stringConstant = switchAndGet();
        }
        System.out.println(stringConstant);
    }

    @Test void largeConstantsTest() {
        String stringConstant = STRING_ONE;
        for (int i = 0; i < 1_000_000; i++) {
            stringConstant = switchAndGet();
        }
        System.out.println(stringConstant);
    }

    private void switchAConstant() {
        for (String constant : constants) {
            switch (constant) {
                case STRING_ONE:
                    System.out.println(constant);
                    break;
                case STRING_TWO:
                    System.out.println(constant);
                    break;
                case STRING_THREE:
                    System.out.println(constant);
                    break;
                case STRING_FOUR:
                    System.out.println(constant);
                    break;
                case STRING_FIVE:
                    System.out.println(constant);
                    break;
                default://
            }
        }
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
}