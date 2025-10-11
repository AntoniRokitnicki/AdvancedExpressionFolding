package data;

import java.util.List;
import java.util.Map;

public class PatternMatchingInstanceofTestData {

    public void main(String arg, int i, Object o, Data data) {
        if [0:"("]o instanceof String[0:")"] {
            [8:"String"] s = [9:"(String) "]o;
            [10:"System.out."]println(s.length());
        }

        if [1:"("]o instanceof Integer[1:")"] {
            [12:"Integer"] num = [13:"(Integer) "]o;
            [14:"System.out."]println(num * 2);
        }

        if [2:"("]o instanceof List[2:")"] {
            [16:"List<?>"] list = [17:"(List<?>) "]o;
            [18:"System.out."]println(list.size());
        }

        if [3:"("]o instanceof Map[3:")"] {
            [20:"Map<?, ?>"] map = [21:"(Map<?, ?>) "]o;
            [22:"System.out."]println(map.keySet());
        }

        if [4:"("]o instanceof Data[4:")"] {
            [24:"Data"] d = [25:"(Data) "]o;
            [26:"System.out."]println(d.[27:"getValue()"]);
        }

        if [5:"("]o instanceof int[][5:")"] {
            [30:"int[]"] arr = [31:"(int[]) "]o;
            [32:"System.out."]println(arr.length);
        }


        if [6:"("]o instanceof DayOfWeek[6:")"] {
            [34:"DayOfWeek"] day = [35:"(DayOfWeek) "]o;
            [36:"System.out."]println(day.name());
        }
    }

    static class Data {
        [37:"public"][37:" "][37:"int"][37:" "]getValue[37:"()"] {[37:"\n            "][37:"return"][37:" "]42[37:";"][37:"\n        "]}
    }

    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // Test for type matching
    static class TypeMatchingTest {
        public void positiveTest(Object o) {
            if [38:"("]o instanceof String[38:")"] {
                [40:"String"] s = [41:"(String) "]o;
                [42:"System.out."]println(s.length());
            }
        }

        public void negativeTest(Object o) {
            if [43:"("]o instanceof String[43:")"] {
                [45:"Integer"] i = [46:"(Integer) "]o; // Type mismatch
                [47:"System.out."]println(i);
            }
        }
    }

    // Test for variable usage in instanceof
    static class VariableUsageTest {
        public void positiveTest(Object o) {
            if [48:"("]o instanceof String[48:")"] {
                [50:"String"] s = [51:"(String) "]o;
                [52:"System.out."]println(s.length());
            }
        }

        public void negativeTest(Object o) {
            if [53:"("][55:"getObject()"] instanceof String[53:")"] { // Method call instead of variable
                [56:"String"] s = [57:"(String) "]o;
                [58:"System.out."]println(s.length());
            }
        }

        private Object getObject() {[59:"\n            "][59:"return"][59:" "]new Object()[59:";"][59:"\n        "]}
    }

    // Test for simple assignment
    static class SimpleAssignmentTest {
        public void positiveTest(Object o) {
            if [60:"("]o instanceof String[60:")"] {
                [62:"String"] s = [63:"(String) "]o;
                [64:"System.out."]println(s.length());
            }
        }

        public void negativeTest(Object o) {
            if [65:"("]o instanceof String[65:")"] {
                [67:"String"] s = "Hello"; // Different assignment
                [68:"System.out."]println(s.length());
            }
        }
    }

    // Test for cast assignment
    static class CastAssignmentTest {
        public void positiveTest(Object o) {
            if [69:"("]o instanceof String[69:")"] {
                [71:"String"] s = [72:"(String) "]o;
                [73:"System.out."]println(s.length());
            }
        }

        public void negativeTest(Object o) {
            if [74:"("]o instanceof String[74:")"] {
                [76:"String"] s = [77:"(String) "]"Hello"; // Cast of a different object
                [78:"System.out."]println(s.length());
            }
        }
    }
}