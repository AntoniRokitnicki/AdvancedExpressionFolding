package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) <fold text='{...}' expand='true'>{
        var dup = data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && 1 == 1
                && data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;

        var bad = data != null && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        var bad1 = data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null;

        if (data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null && data.<fold text='data1' expand='false'>getData1()</fold>.
                <fold text='data2' expand='false'>getData2()</fold>
                .<fold text='data3' expand='false'>getData3()</fold> != null) <fold text='{...}' expand='true'>{
            System.out.println(1);
        }</fold>
        if (data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null) <fold text='{...}' expand='true'>{
            System.out.println(2);
        }</fold>
        if (data != null && data.<fold text='active' expand='false'>isActive()</fold>) <fold text='{...}' expand='true'>{
            System.out.println(3);
        }</fold>
        if (data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null
                && data != null
                && data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && 1 == 1
        ) <fold text='{...}' expand='true'>{
            System.out.println(4);
        }</fold>
        boolean has =  data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null;
        var active = data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        var inactive = data != null && !data.<fold text='active' expand='false'>isActive()</fold>;
        while (data != null && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>) <fold text='{...}' expand='true'>{
            active = !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        }</fold>
    }</fold>

    public void equalsTrue(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if ((data != null && data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold>)) <fold text='{...}' expand='true'>{
            System.out.println("Conditions met!");
        }</fold>
    }</fold>

    public void equalsFalse(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if ((data != null && data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                !data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold>)) <fold text='{...}' expand='true'>{
            System.out.println("Conditions met!");
        }</fold>
    }</fold>
    public void checkConditions(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if ((flag
                || data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>)

                && (data != null
                && data.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold> ||

                data != null
                        && data.<fold text='data3' expand='false'>getData3()</fold> != null
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold>
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold>.<fold text='active' expand='false'>isActive()</fold>) ||

                (data != null
                        && data.<fold text='data5' expand='false'>getData5()</fold> != null
                        && data.<fold text='data5' expand='false'>getData5()</fold>.<fold text='active' expand='false'>isActive()</fold>) &&
                        (flag && flag || flag &&

                                data != null &&
                                data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                                data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold>)) <fold text='{...}' expand='true'>{
            System.out.println("Conditions met!");
        }</fold>
    }</fold>

    public void notFullRoll(Data data) <fold text='{...}' expand='true'>{
        Data data2 = data;
        if (data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold>) {

        }

        if (data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold>) {

        }


        if (data2.<fold text='data1' expand='false'>getData1()</fold> != null &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>) {

        }
    }</fold>

    static class Data <fold text='{...}' expand='true'>{
        public Data getData1()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>
        public Data getData2()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>
        public Data getData3()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>
        public Data getData4()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>
        public Data getData5() <fold text='{...}' expand='true'>{ return null; }</fold>
        public Data getData6() <fold text='{...}' expand='true'>{ return null; }</fold>
        public boolean isActive() <fold text='{...}' expand='true'>{return true; }</fold>
    }</fold>
}
