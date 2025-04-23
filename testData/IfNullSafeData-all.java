<fold text='📦' expand='false'>package</fold> data;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> IfNullSafeData {
    public <fold text='💀' expand='false'>void</fold> enter(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>var</fold> threeChains = <fold text='data?.data1 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold></fold>
                && <fold text='data?.data1 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold></fold>
                && data != <fold text='🕳️' expand='false'>null</fold>
                && <fold text='data?.data1?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>;

        <fold text='val' expand='false'>var</fold> notChain = data != <fold text='🕳️' expand='false'>null</fold> && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold>;
        <fold text='val' expand='false'>var</fold> chain = <fold text='data?.data1?.data4 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data4' expand='false'>getData4()</fold> != <fold text='🕳️' expand='false'>null</fold></fold>;

        if <fold text='' expand='false'>(</fold><fold text='data?.data1?.data2?.data3 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold> &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='data1' expand='false'>getData1()</fold>.
                <fold text='data2' expand='false'>getData2()</fold>
                .<fold text='data3' expand='false'>getData3()</fold> != <fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'></fold>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.data1?.data2?.data3 != null"' expand='false'>"data?.data1?.data2?.data3 != null"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.data1 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold></fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.data1 != null"' expand='false'>"data?.data1 != null"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='active' expand='false'>isActive()</fold></fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"data?.active == true"' expand='false'>"data?.active == true"</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold><fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != <fold text='🕳️' expand='false'>null</fold></fold>
                && data != <fold text='🕳️' expand='false'>null</fold>
                && <fold text='data?.data1?.active == false' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && !data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>
        <fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"2chainz"' expand='false'>"2chainz"</fold>);
        }</fold>
        <fold text='val' expand='false'>boolean</fold> has = <fold text='data?.data1?.data2?.data3?.data4 != null' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != <fold text='🕳️' expand='false'>null</fold></fold>;
        <fold text='var' expand='false'>var</fold> active = <fold text='data?.data1?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>;
        <fold text='val' expand='false'>var</fold> inactive = <fold text='data?.active == false' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> && !data.<fold text='active' expand='false'>isActive()</fold></fold>;
        <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='data?.data2?.active == false' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> && data.<fold text='data2' expand='false'>getData2()</fold> != <fold text='🕳️' expand='false'>null</fold> && !data.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
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
                || <fold text='data?.data1?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)

                && (<fold text='data?.data2?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data2' expand='false'>getData2()</fold> != <fold text='🕳️' expand='false'>null</fold>
                && data.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold></fold> ||

                <fold text='data?.data3?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold>
                        && data.<fold text='data3' expand='false'>getData3()</fold> != <fold text='🕳️' expand='false'>null</fold>
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>
                        && <fold text='data.data3.data4?.active == true' expand='false'>data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold> != <fold text='🕳️' expand='false'>null</fold>
                        && data.<fold text='data3' expand='false'>getData3()</fold>.<fold text='data4' expand='false'>getData4()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>) ||

                (<fold text='data?.data5?.active == true' expand='false'>data != null
                        && data.getData5() != null
                        && data.getData5().isActive()</fold>) &&
                        (flag && flag || flag &&

                                <fold text='data?.data6?.active == true' expand='false'>data != <fold text='🕳️' expand='false'>null</fold> &&
                                data.<fold text='data6' expand='false'>getData6()</fold> != <fold text='🕳️' expand='false'>null</fold> &&
                                data.<fold text='data6' expand='false'>getData6()</fold>.<fold text='active' expand='false'>isActive()</fold></fold>)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Conditions met!"' expand='false'>"Conditions met!"</fold>);
        }</fold>
    }</fold>

    public <fold text='💀' expand='false'>void</fold> notFullRoll(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> data2 = data;
        if <fold text='' expand='false'>(</fold><fold text='data.data1.data2.data3?.active == true' expand='false'>data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold> != <fold text='🕳️' expand='false'>null</fold> &&
                data.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='data3' expand='false'>getData3()</fold>.<fold text='active' expand='false'>isActive()</fold><fold text='' expand='false'></fold>)</fold> {

        }

        if <fold text='' expand='false'>(</fold><fold text='data2.data1.data2?.active == true' expand='false'>data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold> != <fold text='🕳️' expand='false'>null</fold> &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='data2' expand='false'>getData2()</fold>.<fold text='active' expand='false'>isActive()</fold><fold text='' expand='false'></fold>)</fold> {

        }


        if <fold text='' expand='false'>(</fold><fold text='data2.data1?.active == true' expand='false'>data2.<fold text='data1' expand='false'>getData1()</fold> != <fold text='🕳️' expand='false'>null</fold> &&
                data2.<fold text='data1' expand='false'>getData1()</fold>.<fold text='active' expand='false'>isActive()</fold></fold><fold text='' expand='false'>)</fold> {

        }
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{
        public Data getData1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null<fold text='' expand='true'></fold>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>

        public Data getData3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData4()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData5()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data getData6()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='false'></fold>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='🔘' expand='false'>boolean</fold> isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='false'></fold>return</fold><fold text='' expand='true'> </fold><fold text='✅' expand='false'>true</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
