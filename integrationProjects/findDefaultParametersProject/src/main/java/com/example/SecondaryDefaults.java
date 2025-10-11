package com.example;

public class SecondaryDefaults {

    public int compute(int base, double multiplier, boolean round) {
        double value = base * multiplier;
        return round ? (int) Math.round(value) : (int) value;
    }

    public int compute(int base, double multiplier) {
        return compute(base, multiplier, false);
    }

    public int compute(int base) {
        return compute(base, 1.5, true);
    }
}
