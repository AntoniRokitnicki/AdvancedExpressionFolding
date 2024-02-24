package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>List<String></fold> list = <fold text='[' expand='false'>Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>)</fold>;
        list<fold text='[' expand='false'>.set(</fold>1<fold text='] = ' expand='false'>,</fold>"three"<fold text='' expand='false'> )</fold>;
        System.out.println(list<fold text='.last' expand='false'>.get</fold>(<fold text='' expand='false'>list.size() - 1</fold>));
        System.out.println(args<fold text='.last()' expand='false'>[args.length - 1]</fold>);
        <fold text='val' expand='false'>HashMap<String, Integer></fold> map = new HashMap<>();
        map<fold text='[' expand='false'>.put(</fold>"one"<fold text='] = ' expand='false'>, </fold>1<fold text='' expand='false'>)</fold>;
        System.out.println(map<fold text='[' expand='false'>.get(</fold>"two"<fold text=']' expand='false'>)</fold>);
        <fold text='val' expand='false'>List<String></fold> singleton = <fold text='[' expand='false'>java.util.Collections.singletonList(</fold><fold text='"one"' expand='false'>"one"</fold><fold text=']' expand='false'>)</fold>;
        System.out.println(singleton);
        <fold text='val' expand='false'>List<String></fold> asList = <fold text='[' expand='false'>Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>)</fold>;
        System.out.println(asList);
        <fold text='val' expand='false'>List<String></fold> copy = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>))</fold>;
        System.out.println(copy);
        <fold text='val' expand='false'>List<String></fold> unmodifiable = <fold text='[' expand='false'>Collections.unmodifiableList(Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>))</fold>;
        System.out.println(unmodifiable);
        <fold text='val' expand='false'>Set<String></fold> set = <fold text='[' expand='false'>new HashSet<String>() </fold><fold text='' expand='false'><fold text='{...}' expand='true'>{
            <fold text='' expand='false'><fold text='{...}' expand='true'></fold>{
                add(</fold>"one"<fold text=', ' expand='false'>);
                add(</fold>"two"<fold text='' expand='false'>);
            }</fold></fold><fold text='' expand='false'>
        </fold><fold text=']' expand='false'>}</fold></fold>;
        System.out.println(set);
        <fold text='val' expand='false'>Set<String></fold> copyOfSet = <fold text='[' expand='false'>Collections.unmodifiableSet(new HashSet<String>() </fold><fold text='' expand='false'><fold text='{...}' expand='true'>{
            </fold><fold text='' expand='false'><fold text='{...}' expand='true'>{
                add(</fold>"one"<fold text=', ' expand='false'>);
                add(</fold>"two"<fold text='' expand='false'>);
            }</fold></fold><fold text='' expand='false'>
        </fold><fold text=']' expand='false'>}</fold><fold text='' expand='false'></fold>)</fold>;
        System.out.println(copyOfSet);
        <fold text='val' expand='false'>String[]</fold> strings = <fold text='[' expand='false'>new String[] {</fold>"one", "two"<fold text=']' expand='false'>}</fold>;
        System.out.println(Arrays.toString(strings));
    }</fold>
}
