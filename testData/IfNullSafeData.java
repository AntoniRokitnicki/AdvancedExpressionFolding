package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) <fold text='{...}' expand='true'>{
        var threeChains = <fold text='data?.data1 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null</fold>
                && <fold text='data?.data1 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null</fold>
                && data != null
                && <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>;

        var notChain = data != null && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        var chain = <fold text='data?.data1?.data4 != null' expand='false'>data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null</fold>;

        if (<fold text='data?.data1?.data2?.data3 != null' expand='false'>data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null && data.<fold text='data1' expand='false'>getData1()</fold>.
                <fold text='data2' expand='false'>getData2()</fold>
                .<fold text='data3' expand='false'>getData3()</fold> != null</fold>) <fold text='{...}' expand='true'>{
            System.out.println("data?.data1?.data2?.data3 != null");
        }</fold>
        if (<fold text='data?.data1 != null' expand='false'>data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null</fold>) <fold text='{...}' expand='true'>{
            System.out.println("data?.data1 != null");
        }</fold>
        if (<fold text='data?.active == true' expand='false'>data != null && data.<fold text='active' expand='false'>isActive()</fold></fold>) <fold text='{...}' expand='true'>{
            System.out.println("data?.active == true");
        }</fold>
        if (<fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null</fold>
                && data != null
                && <fold text='data?.data1?.active == false' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>
        ) <fold text='{...}' expand='true'>{
            System.out.println("2chainz");
        }</fold>
        boolean has = <fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null</fold>;
        var active = <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>;
        var inactive = <fold text='data?.active == false' expand='false'>data != null && !data.<fold text='active' expand='false'>isActive()</fold></fold>;
        while (<fold text='data?.data2?.active == false' expand='false'>data != null && data.<fold text='data2' expand='false'>getData2()</fold> != null && !data.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) <fold text='{...}' expand='true'>{
            active = !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        }</fold>
    }</fold>

    public void equalsTrue(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if ((<fold text='data?.data6?.active == true' expand='false'>data != null && data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)) <fold text='{...}' expand='true'>{
            System.out.println("Conditions met!");
        }</fold>
    }</fold>

    public void equalsFalse(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if ((<fold text='data?.data6?.active == false' expand='false'>data != null && data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                !data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)) <fold text='{...}' expand='true'>{
            System.out.println("Conditions met!");
        }</fold>
    }</fold>

    public void checkConditions(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if ((flag
                || <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)

                && (<fold text='data?.data2?.active == true' expand='false'>data != null
                && data.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold> ||

                <fold text='data?.data3?.active == true' expand='false'>data != null
                        && data.<fold text='data3' expand='false'>getData3()</fold> != null
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>
                        && <fold text='data.data3.data4?.active == true' expand='false'>data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) ||

                (<fold text='data?.data5?.active == true' expand='false'>data != null
                        && data.<fold text='data5' expand='false'>getData5()</fold> != null
                        && data.<fold text='data5' expand='false'>getData5()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) &&
                        (flag && flag || flag &&

                                <fold text='data?.data6?.active == true' expand='false'>data != null &&
                                data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                                data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)) <fold text='{...}' expand='true'>{
            System.out.println("Conditions met!");
        }</fold>
    }</fold>

    public void notFullRoll(Data data) <fold text='{...}' expand='true'>{
        Data data2 = data;
        if (<fold text='data.data1.data2.data3?.active == true' expand='false'>data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) {

        }

        if (<fold text='data2.data1.data2?.active == true' expand='false'>data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) {

        }


        if (<fold text='data2.data1?.active == true' expand='false'>data2.<fold text='data1' expand='false'>getData1()</fold> != null &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) {

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

        public Data getData5()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>

        public Data getData6()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>

        public boolean isActive()<fold text=' { ' expand='false'> {
            </fold>return true;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
