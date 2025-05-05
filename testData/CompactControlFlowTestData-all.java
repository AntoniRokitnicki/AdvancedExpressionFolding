package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>);
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(arg);
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text=', ' expand='false'>; i < </fold>args.length<fold text=')' expand='false'>; i++</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(i);
        }</fold>
        while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>);
        break;
        }</fold>
        do <fold text='{...}' expand='true'>{
        break;
        }</fold> while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold>;
        switch <fold text='' expand='false'>(</fold>args.length<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
        case 0:
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>);
        }</fold>
            try <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>);
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                e.printStackTrace();
        }</fold>
            if (true)<fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>);
        }</fold>
    }</fold>
}
