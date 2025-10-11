package data;

import java.time.LocalDate;

public class LocalDateLiteralPostfixTestData {
    public static void main(String[] args) {
        LocalDate d1 = [0:"LocalDate.of("]2018[0:", "]01[0:", "]10[0:")"];
        LocalDate d4 = [1:"LocalDate.of("]2018[1:", "]01[1:", "]10[1:")"];
        LocalDate d2 = [2:"LocalDate.of("]2018[2:", "]12[2:", "]10[2:")"];
        LocalDate d3 = [3:"LocalDate.of("]2018[3:",  "]4[3:" ,  "]4[3:"   )"];
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);
        boolean d1SmallerOrEqualD2 = !d1.isAfter([4:"LocalDate.of("]2013[4:", "]1[4:", "]10[4:")"]);
    }
}