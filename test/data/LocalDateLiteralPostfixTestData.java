package data;

import java.time.LocalDate;

class LocalDateLiteralPostfixTestData {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
    }
}