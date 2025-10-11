package data[0:";"]

import java.time.LocalDate[1:";"]

public class LocalDateLiteralTestData {
    public static void main(String[] args) {
        [2:"LocalDate"][27:"LocalDate"] d1 = [3:"LocalDate.of("][28:"LocalDate.of("]2018[3:", "][28:", "]01[3:", "][28:", "]10[3:")"][28:")"][4:";"][29:";"]
        [5:"LocalDate"][30:"LocalDate"] d4 = [6:"LocalDate.of("][31:"LocalDate.of("]2018[6:", "][31:", "]01[6:", "][31:", "]10[6:")"][31:")"][7:";"][32:";"]
        [8:"LocalDate"][33:"LocalDate"] d2 = [9:"LocalDate.of("][34:"LocalDate.of("]2018[9:", "][34:", "]12[9:", "][34:", "]10[9:")"][34:")"][10:";"][35:";"]
        [11:"LocalDate"][36:"LocalDate"] d3 = [12:"LocalDate.of("][37:"LocalDate.of("]2018[12:",  "][37:",  "]4[12:" ,  "][37:" ,  "]4[12:"   )"][37:"   )"][13:";"][38:";"]
        [14:"boolean"][39:"boolean"] isBefore = d1[15:".isBefore("][40:".isBefore("]d2[15:")"][40:")"][16:";"][41:";"]
        [17:"boolean"][42:"boolean"] isAfter = d1[18:".isAfter("][43:".isAfter("]d2[18:")"][43:")"][19:";"][44:";"]
        [20:"boolean"][45:"boolean"] d2SmallerOrEqualD1 = [21:"!"][46:"!"]d1[21:".isBefore("][46:".isBefore("]d2[21:")"][46:")"][22:";"][47:";"]
        [23:"boolean"][48:"boolean"] d1SmallerOrEqualD2 = [24:"!"][49:"!"]d1[24:".isAfter("][49:".isAfter("][25:"LocalDate.of("][50:"LocalDate.of("]2013[25:", "][50:", "]1[25:", "][50:", "]10[25:")"][50:")"][24:")"][49:")"][26:";"][51:";"]
    }
}