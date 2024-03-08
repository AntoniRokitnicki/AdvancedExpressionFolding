package data;

import java.time.LocalDate;

public class LocalDateLiteralTestData {
    public static void main(String[] args) {
        LocalDate d1 = 2018-01-10;
        LocalDate d4 = 2018-01-10;
        LocalDate d2 = 2018-12-10;
        LocalDate d3 = 2018-04-04;
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013-01-10);
    }
}