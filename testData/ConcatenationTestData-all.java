<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.util.*;
<fold text='🚢' expand='false'>import</fold> java.util.stream.Collectors;</fold>

public class ConcatenationTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>List<String></fold> list = Arrays.asList(args);
        list<fold text=' += ' expand='false'>.add(</fold>"one"<fold text='' expand='false'>)</fold>;
        list<fold text=' -= ' expand='false'>.remove(</fold>"one"<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(list.add("two"));
        <fold text='' expand='false'>System.out.</fold>println(list.remove("two"));
        <fold text='val' expand='false'>List<String></fold> singleton = Collections.emptyList();
        list<fold text=' += ' expand='false'>.addAll(</fold>singleton<fold text='' expand='false'>)</fold>;
        list<fold text=' -= ' expand='false'>.removeAll(</fold>singleton<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>Collections.addAll(</fold>list<fold text=' += ' expand='false'>, </fold>args<fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>Set<String></fold> set = new HashSet<>();
        set<fold text=' += ' expand='false'>.add(</fold>"three"<fold text='' expand='false'>)</fold>;
        set<fold text=' -= ' expand='false'>.remove(</fold>"three"<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(set);
        <fold text='val' expand='false'>Set<String></fold> copyOfSet = new HashSet<>();
        set<fold text=' += ' expand='false'>.addAll(</fold>copyOfSet<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(copyOfSet);
        <fold text='var' expand='false'>List<String></fold> streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);

        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);
        streamToList = list.stream()<fold text='*.' expand='false'>.map(</fold><fold text='toUpperCase()' expand='false'>String::toUpperCase</fold><fold text='' expand='false'>)</fold><fold text='.' expand='false'>.collect(Collectors.</fold>toList()<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(streamToList);

        <fold text='val' expand='false'>long</fold> count = streamToList<fold text='.' expand='false'>.stream().</fold>distinct().count();
        <fold text='' expand='false'>System.out.</fold>println(count);
    }</fold>
}
