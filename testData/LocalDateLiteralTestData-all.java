package data<fold text='' expand='false'>;</fold>

import java.time.LocalDate<fold text='' expand='false'>;</fold>

public class LocalDateLiteralTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>LocalDate</fold> d1 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>01<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>LocalDate</fold> d4 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>01<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>LocalDate</fold> d2 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>12<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>LocalDate</fold> d3 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-0' expand='false'>,  </fold>4<fold text='M-0' expand='false'> ,  </fold>4<fold text='D' expand='false'>   )</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> isBefore = d1<fold text=' < ' expand='false'>.isBefore(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> isAfter = d1<fold text=' > ' expand='false'>.isAfter(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> d2SmallerOrEqualD1 = <fold text='' expand='false'>!</fold>d1<fold text=' ≥ ' expand='false'>.isBefore(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> d1SmallerOrEqualD2 = <fold text='' expand='false'>!</fold>d1<fold text=' ≤ ' expand='false'>.isAfter(</fold><fold text='' expand='false'>LocalDate.of(</fold>2013<fold text='Y-0' expand='false'>, </fold>1<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
    }</fold>
}