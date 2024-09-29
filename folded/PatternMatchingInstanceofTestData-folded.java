package data;

import java.util.List;
import java.util.Map;

public class PatternMatchingInstanceofTestData {

    public void main(String arg, int i, Object o, Data data) {
        if (o instanceof String s) {
            System.out.println(s.length());
        }

        if (o instanceof Integer num) {
            System.out.println(num * 2);
        }

        if (o instanceof List list) {
            System.out.println(list.size());
        }

        if (o instanceof Map map) {
            System.out.println(map.keySet());
        }

        if (o instanceof Data d) {
            System.out.println(d.getValue());
        }

        if (o instanceof int[] arr) {
            System.out.println(arr.length);
        }


        if (o instanceof DayOfWeek day) {
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