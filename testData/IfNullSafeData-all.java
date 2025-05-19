package data;

<fold text='' expand='true'>@SuppressWarnings("ALL")</fold><fold text='' expand='true'>
</fold>public class IfNullSafeData {
    public void enter(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>var</fold> threeChains = <fold text='data?.data1 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null</fold>
                && <fold text='data?.data1 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null</fold>
                && data != null
                && <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>;

        <fold text='val' expand='false'>var</fold> notChain = data != null && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        <fold text='val' expand='false'>var</fold> chain = <fold text='data?.data1?.data4 != null' expand='false'>data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null</fold>;

        if <fold text='' expand='false'>(</fold><fold text='data?.data1?.data2?.data3 != null' expand='false'>data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null && data.<fold text='data1' expand='false'>getData1()</fold>.
                <fold text='data2' expand='false'>getData2()</fold>
                .<fold text='data3' expand='false'>getData3()</fold> != null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.data1?.data2?.data3 != null"' expand='false'>"data?.data1?.data2?.data3 != null"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.data1 != null' expand='false'>data != null && data.<fold text='data1' expand='false'>getData1()</fold> != null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.data1 != null"' expand='false'>"data?.data1 != null"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.active == true' expand='false'>data != null && data.<fold text='active' expand='false'>isActive()</fold></fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.active == true"' expand='false'>"data?.active == true"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null</fold>
                && data != null
                && <fold text='data?.data1?.active == false' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>
        <fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"2chainz"' expand='false'>"2chainz"</fold>);
        }</fold>
        <fold text='val' expand='false'>boolean</fold> has = <fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != null</fold>;
        <fold text='var' expand='false'>var</fold> active = <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.<fold text='data1' expand='false'>getData1()</fold> != null
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>;
        <fold text='val' expand='false'>var</fold> inactive = <fold text='data?.active == false' expand='false'>data != null && !data.<fold text='active' expand='false'>isActive()</fold></fold>;
        while <fold text='' expand='false'>(</fold><fold text='data?.data2?.active == false' expand='false'>data != null && data.<fold text='data2' expand='false'>getData2()</fold> != null && !data.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            active = !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        }</fold>
    }</fold>

    public void equalsTrue(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>(<fold text='data?.data6?.active == true' expand='false'>data != null && data.getData6() != null &&
                data.getData6().isActive()</fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public void equalsFalse(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>(<fold text='data?.data6?.active == false' expand='false'>data != null && data.getData6() != null &&
                !data.getData6().isActive()</fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public void checkConditions(Data data, boolean flag) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>(flag
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
                        && data.getData5() != null
                        && data.getData5().isActive()</fold>) &&
                        (flag && flag || flag &&

                                <fold text='data?.data6?.active == true' expand='false'>data != null &&
                                data.<fold text='data6' expand='false'>getData6()</fold> != null &&
                                data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public void notFullRoll(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> data2 = data;
        if <fold text='' expand='false'>(</fold><fold text='data.data1.data2.data3?.active == true' expand='false'>data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != null &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold><fold text='' expand='false'></fold>)</fold> {

        }

        if <fold text='' expand='false'>(</fold><fold text='data2.data1.data2?.active == true' expand='false'>data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != null &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold><fold text='' expand='false'>)</fold> {

        }


        if <fold text='' expand='false'>(</fold><fold text='data2.data1?.active == true' expand='false'>data2.<fold text='data1' expand='false'>getData1()</fold> != null &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold><fold text='' expand='false'></fold>)</fold> {

        }
    }</fold>

    static class Data <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>Data</fold><fold text='' expand='true'> </fold>getData1<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>Data</fold><fold text='' expand='true'> </fold>getData2<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>Data</fold><fold text='' expand='true'> </fold>getData3<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>Data</fold><fold text='' expand='true'> </fold>getData4<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>Data</fold><fold text='' expand='true'> </fold>getData5<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>Data</fold><fold text='' expand='true'> </fold>getData6<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>boolean</fold><fold text='' expand='true'> </fold>isActive<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>true<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
