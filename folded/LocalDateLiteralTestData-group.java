package data;

import java.time.LocalDate;

public class LocalDateLiteralTestData {
    public static void main(String[] args) {
        LocalDate d1 = [0:"LocalDate.of("][5:"LocalDate.of("]2018[0:", "][5:", "]01[0:", "][5:", "]10[0:")"][5:")"];
        LocalDate d4 = [1:"LocalDate.of("][6:"LocalDate.of("]2018[1:", "][6:", "]01[1:", "][6:", "]10[1:")"][6:")"];
        LocalDate d2 = [2:"LocalDate.of("][7:"LocalDate.of("]2018[2:", "][7:", "]12[2:", "][7:", "]10[2:")"][7:")"];
        LocalDate d3 = [3:"LocalDate.of("][8:"LocalDate.of("]2018[3:",  "][8:",  "]4[3:" ,  "][8:" ,  "]4[3:"   )"][8:"   )"];
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);
        boolean d1SmallerOrEqualD2 = !d1.isAfter([4:"LocalDate.of("][9:"LocalDate.of("]2013[4:", "][9:", "]1[4:", "][9:", "]10[4:")"][9:")"]);
    }
}