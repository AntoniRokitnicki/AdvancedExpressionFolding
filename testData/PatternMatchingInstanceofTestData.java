package data;

import java.util.List;
import java.util.Map;

public class PatternMatchingInstanceofTestData {

    public void main(String arg, int i, Object o, Data data) {
        if (o instanceof String) {
            String s = (String) o;
            System.out.println(s.length());
        }

        if (o instanceof Integer) {
            Integer num = (Integer) o;
            System.out.println(num * 2);
        }

        if (o instanceof List) {
            List<?> list = (List<?>) o;
            System.out.println(list.size());
        }

        if (o instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) o;
            System.out.println(map.keySet());
        }

        if (o instanceof Data) {
            Data d = (Data) o;
            System.out.println(d.getValue());
        }

        if (o instanceof int[]) {
            int[] arr = (int[]) o;
            System.out.println(arr.length);
        }

        if (o instanceof DayOfWeek) {
            DayOfWeek day = (DayOfWeek) o;
            System.out.println(day.name());
        }
    }

    static class Data {
        public int getValue() {
            return 42;
        }
    }

    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}