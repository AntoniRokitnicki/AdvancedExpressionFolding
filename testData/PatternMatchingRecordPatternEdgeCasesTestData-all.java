package data;

public class PatternMatchingRecordPatternEdgeCasesTestData {

    record Box<T>(T value) {}
    record ComponentAlias(Box<String> box, int count) {}
    record IntBox(int value) {}

    public void componentMethodVariants(Object o) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>o instanceof ComponentAlias<fold text=')' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>ComponentAlias</fold> alias = <fold text='' expand='false'>(ComponentAlias) </fold>o;
            <fold text='' expand='false'><fold text='val' expand='false'>final</fold> Box<String></fold> box = alias.<fold text='box' expand='false'>box()</fold>;
            <fold text='' expand='false'><fold text='val' expand='false'>final</fold> int</fold> count = alias.<fold text='count' expand='false'>count()</fold>;
            <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>box<fold text='' expand='false'> + "</fold> <fold text='$' expand='false'>" + </fold>count<fold text='")' expand='false'>)</fold>;
        }</fold>
    }</fold>

    public void finalBinding(Object o) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>o instanceof IntBox<fold text=')' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>IntBox</fold> box = <fold text='' expand='false'>(IntBox) </fold>o;
            <fold text='' expand='false'><fold text='val' expand='false'>final</fold> int</fold> value = box.<fold text='value' expand='false'>value()</fold>;
            <fold text='' expand='false'>System.out.</fold>println(value);
        }</fold>
    }</fold>

    static class NegativeCases <fold text='{...}' expand='true'>{
        public void mismatchedAccessOrder(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof ComponentAlias<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>ComponentAlias</fold> alias = <fold text='' expand='false'>(ComponentAlias) </fold>o;
                <fold text='val' expand='false'>int</fold> count = alias.<fold text='count' expand='false'>count()</fold>;
                <fold text='val' expand='false'>Box<String></fold> box = alias.<fold text='box' expand='false'>box()</fold>;
                <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>box<fold text='' expand='false'> + "</fold> <fold text='$' expand='false'>" + </fold>count<fold text='")' expand='false'>)</fold>;
            }</fold>
        }</fold>

        public void usesRecordVariable(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o instanceof IntBox<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>IntBox</fold> box = <fold text='' expand='false'>(IntBox) </fold>o;
                <fold text='val' expand='false'>int</fold> value = box.<fold text='value' expand='false'>value()</fold>;
                <fold text='' expand='false'>System.out.</fold>println(box);
                <fold text='' expand='false'>System.out.</fold>println(value);
            }</fold>
        }</fold>
    }</fold>
}
