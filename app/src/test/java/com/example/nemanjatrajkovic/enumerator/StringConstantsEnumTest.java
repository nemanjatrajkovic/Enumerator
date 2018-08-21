package com.example.nemanjatrajkovic.enumerator;

import org.junit.jupiter.api.Test;

import static com.example.nemanjatrajkovic.enumerator.StringConstantsEnum.First;
import static org.junit.jupiter.api.Assertions.*;

class StringConstantsEnumTest {

    private static final StringConstantsEnum[] enumValues = StringConstantsEnum.values();

    @Test void simpleConstantsTest() {
        StringConstantsEnum enumeration = First;
        for (int i = 0; i < 1_000; i++) {
            enumeration = enumerateAndGet();
        }
        System.out.println(enumeration.value);
    }

    @Test void mediumConstantsTest() {
        StringConstantsEnum enumeration = First;
        for (int i = 0; i < 100_000; i++) {
            enumeration = enumerateAndGet();
        }
        System.out.println(enumeration.value);
    }

    @Test void largeConstantsTest() {
        StringConstantsEnum enumeration = First;
        for (int i = 0; i < 1_000_000; i++) {
             enumeration = enumerateAndGet();
        }
        System.out.println(enumeration.value);
    }

    private void enumerate() {
        for (StringConstantsEnum constant : enumValues) {
            switch (constant) {
                case First:
                    System.out.println(constant.value);
                    break;
                case Second:
                    System.out.println(constant.value);
                    break;
                case Third:
                    System.out.println(constant.value);
                    break;
                case Forth:
                    System.out.println(constant.value);
                    break;
                case Fifth:
                    System.out.println(constant.value);
                    break;
                default://
            }
        }
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