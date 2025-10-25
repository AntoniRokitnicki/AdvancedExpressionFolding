package data;

public class PatternMatchingRecordPatternEdgeCasesTestData {

    record Box<T>(T value) {}
    record ComponentAlias(Box<String> box, int count) {}
    record IntBox(int value) {}

    public void componentMethodVariants(Object o) <fold text='{...}' expand='true'>{
        if (o instanceof <fold text='ComponentAlias(final Box<String> box, final int count)' expand='true'>ComponentAlias</fold><fold text=')' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>ComponentAlias alias = (ComponentAlias) o;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>final Box<String> box = alias.box();</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>final int count = alias.count();</fold><fold text='' expand='true'>
            </fold>System.out.println(box + " " + count);
        }</fold>
    }</fold>

    public void finalBinding(Object o) <fold text='{...}' expand='true'>{
        if (o instanceof <fold text='IntBox(final int value)' expand='true'>IntBox</fold><fold text=')' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>IntBox box = (IntBox) o;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>final int value = box.value();</fold><fold text='' expand='true'>
            </fold>System.out.println(value);
        }</fold>
    }</fold>

    static class NegativeCases <fold text='{...}' expand='true'>{
        public void mismatchedAccessOrder(Object o) <fold text='{...}' expand='true'>{
            if (o instanceof ComponentAlias<fold text=' alias)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='true'>ComponentAlias alias = (ComponentAlias) o;</fold><fold text='' expand='true'>
                </fold>int count = alias.count();
                Box<String> box = alias.box();
                System.out.println(box + " " + count);
            }</fold>
        }</fold>

        public void usesRecordVariable(Object o) <fold text='{...}' expand='true'>{
            if (o instanceof IntBox<fold text=' box)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='true'>IntBox box = (IntBox) o;</fold><fold text='' expand='true'>
                </fold>int value = box.value();
                System.out.println(box);
                System.out.println(value);
            }</fold>
        }</fold>
    }</fold>
}
