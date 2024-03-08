package data

import java.time.LocalDate

public class LocalDateTestData {
    public static void main(String[] args) {
        val d1 = 2018Y-12M-10D
        val d2 = 2018Y-12M-10D
        val isBefore = d1 < d2
        val isAfter = d1 > d2
        val d2SmallerOrEqualD1 = d1 ≥ d2
        val d1SmallerOrEqualD2 = d1 ≤ d2
    }
}