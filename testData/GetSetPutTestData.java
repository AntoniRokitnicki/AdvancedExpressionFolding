package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        List<String> list = <fold text='[' expand='false'>Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>)</fold>;
        list<fold text='[' expand='false'>.set(</fold>1<fold text='] = ' expand='false'>,</fold>"three"<fold text='' expand='false'> )</fold>;
        System.out.println(list<fold text='.getLast' expand='false'>.get</fold>(<fold text='' expand='false'>list.size() - 1</fold>));
        System.out.println(args<fold text='.last()' expand='false'>[args.length - 1]</fold>);
        HashMap<String, Integer> map = new HashMap<>();
        map<fold text='[' expand='false'>.put(</fold>"one"<fold text='] = ' expand='false'>, </fold>1<fold text='' expand='false'>)</fold>;
        System.out.println(map<fold text='[' expand='false'>.get(</fold>"two"<fold text=']' expand='false'>)</fold>);
        List<String> singleton = <fold text='[' expand='false'>java.util.Collections.singletonList(</fold><fold text='"one"' expand='false'>"one"</fold><fold text=']' expand='false'>)</fold>;
        System.out.println(singleton);
        List<String> asList = <fold text='[' expand='false'>Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>)</fold>;
        System.out.println(asList);
        List<String> copy = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>))</fold>;
        System.out.println(copy);
        List<String> unmodifiable = <fold text='[' expand='false'>Collections.unmodifiableList(Arrays.asList(</fold><fold text='"one"' expand='false'>"one"</fold>, <fold text='"two"' expand='false'>"two"</fold><fold text=']' expand='false'>))</fold>;
        System.out.println(unmodifiable);
        Set<String> set = <fold text='[' expand='false'>new HashSet<String>() </fold><fold text='' expand='false'><fold text='{...}' expand='true'>{
            <fold text='' expand='false'><fold text='{...}' expand='true'></fold>{
                add(</fold>"one"<fold text=', ' expand='false'>);
                add(</fold>"two"<fold text='' expand='false'>);
            }</fold></fold><fold text='' expand='false'>
        </fold><fold text=']' expand='false'>}</fold></fold>;
        System.out.println(set);
        Set<String> copyOfSet = <fold text='[' expand='false'>Collections.unmodifiableSet(new HashSet<String>() <fold text='' expand='false'><fold text='{...}' expand='true'></fold>{
            </fold><fold text='' expand='false'><fold text='{...}' expand='true'>{
                add(</fold>"one"<fold text=', ' expand='false'>);
                add(</fold>"two"<fold text='' expand='false'>);
            }</fold></fold><fold text='' expand='false'>
        </fold><fold text=']' expand='false'>}</fold></fold><fold text='' expand='false'>)</fold>;
        System.out.println(copyOfSet);
        String[] strings = <fold text='[' expand='false'>new String[] {</fold>"one", "two"<fold text=']' expand='false'>}</fold>;
        System.out.println(Arrays.toString(strings));
        System.out.println(System<fold text='[' expand='false'>.getProperty(</fold>"user.dir"<fold text=']' expand='false'>)</fold>);
        System.out.println(System.getProperty("user.dir", "c:/windows"));
        System.out.println(System.getenv("user.dir"));
        System.out.println(System.getenv()<fold text='[' expand='false'>.get(</fold>"user.dir"<fold text=']' expand='false'>)</fold>);
        list<fold text=' -= ' expand='false'>.remove(</fold>"one"<fold text='' expand='false'>)</fold>;
        set<fold text=' -= ' expand='false'>.remove(</fold>"two"<fold text='' expand='false'>)</fold>;
        map<fold text=' -= ' expand='false'>.remove(</fold>"three"<fold text='' expand='false'>)</fold>;
        list<fold text=' -= ' expand='false'>.removeAll(</fold>copy<fold text='' expand='false'>)</fold>;
    }</fold>
}
