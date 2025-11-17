package data;

public class AppendSetterInterpolatedStringTestData {
    private String name;

    public static void main(String[] args) <fold text='{...}' expand='true'>{
        StringBuilder sb1 = <fold text='' expand='false'>new StringBuilder().append(</fold>args[0]<fold text='' expand='false'>)</fold>;
        sb1<fold text=' += ' expand='false'>.append(</fold>"Hello, <fold text='${' expand='false'>" + </fold><fold text='args[0]}"' expand='false'>args[0]</fold>);
        System.out.println(sb1<fold text='' expand='false'>.toString()</fold>);
        StringBuilder sb2 = <fold text='""' expand='false'>new StringBuilder("")</fold>;
        sb2<fold text=' += ' expand='false'>.append</fold><fold text='"${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!"<fold text='' expand='false'>)</fold>;
        System.out.println(sb2<fold text='' expand='false'>.toString()</fold>);
        StringBuilder sb3 = <fold text='"Hello, "' expand='false'>new StringBuilder("Hello, ")</fold><fold text=' + ' expand='false'>.append(</fold>args[0]<fold text='' expand='false'>)</fold>; // Should be StringBuilder sb3 = "Hello, $(args[0)":
        System.out.println(sb3);

        new AppendSetterInterpolatedStringTestData().<fold text='name = ' expand='false'>setName(</fold>"Hello, <fold text='${' expand='false'>" + </fold><fold text='args[0]}"' expand='false'>args[0]</fold>);
        new AppendSetterInterpolatedStringTestData().<fold text='name = ' expand='false'>setName</fold><fold text='"${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!"<fold text='' expand='false'>)</fold>;

        Event event = new Event();
        event.systemName = args[0];
        java.util.List<String> data = new java.util.ArrayList<>();
        data<fold text=' += ' expand='false'>.add(</fold>"System name = <fold text='${' expand='false'>" + </fold><fold text='event.systemName}"' expand='false'>event.systemName</fold><fold text='' expand='false'>)</fold>;
    }</fold>

    public void setName(String name)<fold text=' { ' expand='false'> {
        </fold>this.name = name;<fold text=' }' expand='false'>
    }</fold>

    private static class Event <fold text='{...}' expand='true'>{
        String systemName;
    }</fold>
}
