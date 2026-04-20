package data;

import <fold text='...' expand='false'>java.util.Arrays;
import java.util.List;</fold>

public class ArraysAsListNegativeNumbersTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        List<Integer> list = <fold text='[' expand='false'>Arrays.asList(</fold>-1, -2, -3<fold text=']' expand='false'>)</fold>;
        System.out.println(list);
    }</fold>
}
