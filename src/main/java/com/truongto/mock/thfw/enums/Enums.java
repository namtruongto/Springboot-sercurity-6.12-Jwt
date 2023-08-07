package com.truongto.mock.thfw.enums;

public class Enums {
    public enum Status {
        SUCCESS, ERROR, WARNING
    }

    public enum Gender{
        MALE(0),
        FEAMLE(1),
        OTHER(2),
        LGBT(3);

        private final int value;

        private Gender(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
