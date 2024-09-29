package data;

import <fold text='...' expand='false'>java.util.List;
import java.util.Map;</fold>

public class PatternMatchingInstanceofTestData {

    public void main(String arg, int i, Object o, Data data) <fold text='{...}' expand='true'>{
        if (o instanceof String<fold text=' s)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>String s = (String) o;</fold><fold text='' expand='true'>
            </fold>System.out.println(s.length());
        }</fold>

        if (o instanceof Integer<fold text=' num)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>Integer num = (Integer) o;</fold><fold text='' expand='true'>
            </fold>System.out.println(num * 2);
        }</fold>

        if (o instanceof List<fold text=' list)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>List<?> list = (List<?>) o;</fold><fold text='' expand='true'>
            </fold>System.out.println(list.size());
        }</fold>

        if (o instanceof Map<fold text=' map)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>Map<?, ?> map = (Map<?, ?>) o;<fold text='' expand='true'></fold>
            </fold>System.out.println(map.keySet());
        }</fold>

        if (o instanceof Data<fold text=' d)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>Data d = (Data) o;</fold><fold text='' expand='true'>
            </fold>System.out.println(d.getValue());
        }</fold>

        if (o instanceof int[]<fold text=' arr)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>int[] arr = (int[]) o;</fold><fold text='' expand='true'>
            </fold>System.out.println(arr.length);
        }</fold>


        if (o instanceof DayOfWeek<fold text=' day)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>DayOfWeek day = (DayOfWeek) o;</fold><fold text='' expand='true'>
            </fold>System.out.println(day.name());
        }</fold>
    }</fold>

    static class Data <fold text='{...}' expand='true'>{
        public int getValue()<fold text=' { ' expand='false'> {
            </fold>return 42;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    enum DayOfWeek <fold text='{...}' expand='true'>{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }</fold>
}