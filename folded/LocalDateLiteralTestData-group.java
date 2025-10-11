package data;

import java.time.LocalDate;

public class LocalDateLiteralTestData {
    public static void main(String[] args) {
        LocalDate d1 = [0:"LocalDate.of("]{[0:"LocalDate.of("]}2018[0:", "]{[0:", "]}01[0:", "]{[0:", "]}10[0:")"]{[0:")"]};
        LocalDate d4 = [0:"LocalDate.of("]{[0:"LocalDate.of("]}2018[0:", "]{[0:", "]}01[0:", "]{[0:", "]}10[0:")"]{[0:")"]};
        LocalDate d2 = [0:"LocalDate.of("]{[0:"LocalDate.of("]}2018[0:", "]{[0:", "]}12[0:", "]{[0:", "]}10[0:")"]{[0:")"]};
        LocalDate d3 = [0:"LocalDate.of("]{[0:"LocalDate.of("]}2018[0:",  "]{[0:",  "]}4[0:" ,  "]{[0:" ,  "]}4[0:"   )"]{[0:"   )"]};
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);
        boolean d1SmallerOrEqualD2 = !d1.isAfter([0:"LocalDate.of("]{[0:"LocalDate.of("]}2013[0:", "]{[0:", "]}1[0:", "]{[0:", "]}10[0:")"]{[0:")"]});
    }
}