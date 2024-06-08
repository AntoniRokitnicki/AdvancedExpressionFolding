<fold text='📦' expand='false'>package</fold> data;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> IfNullSafeData {
    public <fold text='💀' expand='false'>void</fold> enter(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>var</fold> threeChains = <fold text='data?.data1 != null' expand='false'>data != null
                && data.getData1() != null</fold>
                && <fold text='data?.data1 != null' expand='false'>data != null
                && data.getData1() != null</fold>
                && data != <fold text='🕳️' expand='false'>null</fold>
                && <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.getData1() != null
                && data.getData1().isActive()</fold>;

        <fold text='val' expand='false'>var</fold> notChain = data != <fold text='🕳️' expand='false'>null</fold> && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        <fold text='val' expand='false'>var</fold> chain = <fold text='data?.data1?.data4 != null' expand='false'>data != null && data.getData1() != null && data.getData1().getData4() != null</fold>;

        if <fold text='' expand='false'>(</fold><fold text='data?.data1?.data2?.data3 != null' expand='false'>data != null && data.getData1() != null &&
                data.getData1().getData2() != null && data.getData1().
                getData2()
                .getData3() != null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.data1?.data2?.data3 != null"' expand='false'>"data?.data1?.data2?.data3 != null"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.data1 != null' expand='false'>data != null && data.getData1() != null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.data1 != null"' expand='false'>"data?.data1 != null"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.active == true' expand='false'>data != null && data.isActive()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.active == true"' expand='false'>"data?.active == true"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null</fold>
                && data != <fold text='🕳️' expand='false'>null</fold>
                && <fold text='data?.data1?.active == false' expand='false'>data != null
                && data.getData1() != null
                && !data.getData1().isActive()</fold>
        <fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"2chainz"' expand='false'>"2chainz"</fold>);
        }</fold>
        <fold text='val' expand='false'>boolean</fold> has = <fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null</fold>;
        <fold text='var' expand='false'>var</fold> active = <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.getData1() != null
                && data.getData1().isActive()</fold>;
        <fold text='val' expand='false'>var</fold> inactive = <fold text='data?.active == false' expand='false'>data != null && !data.isActive()</fold>;
        <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='data?.data2?.active == false' expand='false'>data != null && data.getData2() != null && !data.getData2().isActive()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            active = !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        }</fold>
    }</fold>

    public <fold text='💀' expand='false'>void</fold> equalsTrue(Data data, <fold text='🔘' expand='false'>boolean</fold> flag) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>(<fold text='data?.data6?.active == true' expand='false'>data != null && data.getData6() != null &&
                data.getData6().isActive()</fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public <fold text='💀' expand='false'>void</fold> equalsFalse(Data data, <fold text='🔘' expand='false'>boolean</fold> flag) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>(<fold text='data?.data6?.active == false' expand='false'>data != null && data.getData6() != null &&
                !data.getData6().isActive()</fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public <fold text='💀' expand='false'>void</fold> checkConditions(Data data, <fold text='🔘' expand='false'>boolean</fold> flag) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>(flag
                || <fold text='data?.data1?.active == true' expand='false'>data != null
                && data.getData1() != null
                && data.getData1().isActive()</fold>)

                && (<fold text='data?.data2?.active == true' expand='false'>data != null
                && data.getData2() != null
                && data.getData2().isActive()</fold> ||

                <fold text='data?.data3?.active == true' expand='false'>data != null
                        && data.getData3() != null
                        && data.getData3().isActive()</fold>
                        && <fold text='data.data3.data4?.active == true' expand='false'>data.getData3().getData4() != null
                        && data.getData3().getData4().isActive()</fold>) ||

                (<fold text='data?.data5?.active == true' expand='false'>data != null
                        && data.getData5() != null
                        && data.getData5().isActive()</fold>) &&
                        (flag && flag || flag &&

                                <fold text='data?.data6?.active == true' expand='false'>data != null &&
                                data.getData6() != null &&
                                data.getData6().isActive()</fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public <fold text='💀' expand='false'>void</fold> notFullRoll(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> data2 = data;
        if <fold text='' expand='false'>(</fold><fold text='data.data1.data2.data3?.active == true' expand='false'>data.getData1().getData2().getData3() != null &&
                data.getData1().getData2().getData3().isActive()</fold><fold text='' expand='false'>)</fold> {

        }

        if <fold text='' expand='false'>(</fold><fold text='data2.data1.data2?.active == true' expand='false'>data2.getData1().getData2() != null &&
                data2.getData1().getData2().isActive()</fold><fold text='' expand='false'>)</fold> {

        }


        if <fold text='' expand='false'>(</fold><fold text='data2.data1?.active == true' expand='false'>data2.getData1() != null &&
                data2.getData1().isActive()</fold><fold text='' expand='false'>)</fold> {

        }
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{
        public Data getData1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData4()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData5()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData6()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='🔘' expand='false'>boolean</fold> isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='✅' expand='false'>true</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
