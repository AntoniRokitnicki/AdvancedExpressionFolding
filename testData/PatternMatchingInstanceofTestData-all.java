package data;

import <fold text='...' expand='false'>java.util.List;
import java.util.Map;</fold>

public class PatternMatchingInstanceofTestData {

    public void main(String arg, int i, Object o, Data data) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(s.length());
        }</fold>

        if <fold text='' expand='false'>(</fold>o instanceof Integer<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Integer</fold> num = <fold text='' expand='false'>(Integer) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(num * 2);
        }</fold>

        if <fold text='' expand='false'>(</fold>o instanceof List<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>List<?></fold> list = <fold text='' expand='false'>(List<?>) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(list.size());
        }</fold>

        if <fold text='' expand='false'>(</fold>o instanceof Map<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Map<?, ?></fold> map = <fold text='' expand='false'>(Map<?, ?>) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(map.keySet());
        }</fold>

        if <fold text='' expand='false'>(</fold>o instanceof Data<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Data</fold> d = <fold text='' expand='false'>(Data) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(d.<fold text='value' expand='false'>getValue()</fold>);
        }</fold>

        if <fold text='' expand='false'>(</fold>o instanceof int[]<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>int[]</fold> arr = <fold text='' expand='false'>(int[]) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(arr.length);
        }</fold>


        if <fold text='' expand='false'>(</fold>o instanceof DayOfWeek<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>DayOfWeek</fold> day = <fold text='' expand='false'>(DayOfWeek) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(day.name());
        }</fold>
    }</fold>

    static class Data <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> <fold text='' expand='true'></fold>int</fold><fold text='' expand='true'> </fold>getValue<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>42<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    enum DayOfWeek <fold text='{...}' expand='true'>{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }</fold>

    // Test for type matching
    static class TypeMatchingTest <fold text='{...}' expand='true'>{
        public void positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public void negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Integer</fold> i = <fold text='' expand='false'>(Integer) </fold>o; // Type mismatch
                <fold text='' expand='false'>System.out.</fold>println(i);
            }</fold>
        }</fold>
    }</fold>

    // Test for variable usage in instanceof
    static class VariableUsageTest <fold text='{...}' expand='true'>{
        public void positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public void negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='object' expand='false'>getObject()</fold> instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{ // Method call instead of variable
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        private Object getObject()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new Object()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    // Test for simple assignment
    static class SimpleAssignmentTest <fold text='{...}' expand='true'>{
        public void positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public void negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = "Hello"; // Different assignment
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>
    }</fold>

    // Test for cast assignment
    static class CastAssignmentTest <fold text='{...}' expand='true'>{
        public void positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public void negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>"Hello"; // Cast of a different object
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>
    }</fold>
}