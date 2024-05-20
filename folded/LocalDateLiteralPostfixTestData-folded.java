package data;

import java.time.LocalDate;

public class LocalDateLiteralPostfixTestData {
    public static void main(String[] args) {
        LocalDate d1 = 2018Y-01M-10D;
        LocalDate d4 = 2018Y-01M-10D;
        LocalDate d2 = 2018Y-12M-10D;
        LocalDate d3 = 2018Y-04M-04D;
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013Y-01M-10D);
    }
}