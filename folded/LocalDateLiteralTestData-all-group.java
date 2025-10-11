package data[0:";"]

import java.time.LocalDate[1:";"]

public class LocalDateLiteralTestData {
    public static void main(String[] args) {
        [2:"LocalDate"] d1 = [3:"LocalDate.of("]2018[3:", "]01[3:", "]10[3:")"][4:";"]
        [5:"LocalDate"] d4 = [6:"LocalDate.of("]2018[6:", "]01[6:", "]10[6:")"][7:";"]
        [8:"LocalDate"] d2 = [9:"LocalDate.of("]2018[9:", "]12[9:", "]10[9:")"][10:";"]
        [11:"LocalDate"] d3 = [12:"LocalDate.of("]2018[12:",  "]4[12:" ,  "]4[12:"   )"][13:";"]
        [14:"boolean"] isBefore = d1[15:".isBefore("]d2[15:")"][16:";"]
        [17:"boolean"] isAfter = d1[18:".isAfter("]d2[18:")"][19:";"]
        [20:"boolean"] d2SmallerOrEqualD1 = [21:"!"]d1[21:".isBefore("]d2[21:")"][22:";"]
        [23:"boolean"] d1SmallerOrEqualD2 = [24:"!"]d1[24:".isAfter("][25:"LocalDate.of("]2013[25:", "]1[25:", "]10[25:")"][24:")"][26:";"]
    }
}