<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>org.apache.commons.lang3.builder.EqualsBuilder;
<fold text='🚢' expand='false'>import</fold> org.apache.commons.lang3.builder.HashCodeBuilder;
<fold text='🚢' expand='false'>import</fold> org.apache.commons.lang3.builder.ToStringBuilder;

<fold text='🚢' expand='false'>import</fold> java.util.Objects;
<fold text='🚢' expand='false'>import</fold> java.util.Optional;
<fold text='🚢' expand='false'>import</fold> java.util.logging.Logger;</fold>

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.extension.lombok.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */</fold>
@SuppressWarnings("ALL")
<fold text='@Builder @Getter @Setter @Serial p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LombokTestData {<fold text='' expand='false'>

    </fold><fold text='🚫' expand='false'><fold text='' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>long</fold> serialVersionUID = 1234567L;</fold>

    LombokTestData data;
    <fold text='🔘' expand='false'>boolean</fold> ok;
    String string;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.string = <fold text='<<' expand='false'>string</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold><fold text='' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public <fold text='🏛️' expand='false'>class</fold> LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            <fold text='@Getter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public <fold text='🏛️' expand='false'>class</fold> LombokSettersPartial <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> LombokSettersFinalField <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> ok = <fold text='✅' expand='false'>true</fold>;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@ToString p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public String toString() <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> "ToStringFull{<fold text='' expand='false'>" +
                    "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='' expand='false'> +
                    "</fold>, ok=<fold text='$' expand='false'>" + </fold>ok<fold text='${' expand='false'> +
                    </fold>'}'<fold text='}";' expand='false'>;</fold>
        }</fold></fold>

        public <fold text='🏛️' expand='false'>class</fold> ToStringPartial <fold text='{...}' expand='true'>{
            <fold text='@ToString L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public String toString() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"ToStringPartial{<fold text='' expand='false'>" +
                        "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='${' expand='false'> +
                        </fold>'}'<fold text='' expand='true'>;</fold><fold text='' expand='true'>
            </fold>}</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> ToStringPartial2 <fold text='{...}' expand='true'>{
            <fold text='@ToString L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public String toString() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"ToStringPartial{<fold text='' expand='false'>" +
                        "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='${' expand='false'> +
                        </fold>'}'<fold text='' expand='true'>;</fold><fold text='' expand='true'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
            <fold text='val' expand='false'>EqualsAndHashCodeFull</fold> that = <fold text='' expand='false'>(EqualsAndHashCodeFull) </fold>o;
            <fold text='🔙' expand='false'>return</fold> ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == <fold text='🕳️' expand='false'>null</fold>);
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            <fold text='🔙' expand='false'>return</fold> result;
        }</fold></fold>

        public <fold text='🏛️' expand='false'>class</fold> EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            <fold text='@EqualsAndHashCode L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>EqualsAndHashCodePartial</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartial) </fold>o;
                <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == <fold text='🕳️' expand='false'>null</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            <fold text='@EqualsAndHashCode L' expand='false'>L</fold>ombokTestData data;
            <fold text='@EqualsAndHashCode b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>EqualsAndHashCodePartialTwo</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartialTwo) </fold>o;
                <fold text='🔙' expand='false'>return</fold> ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == <fold text='🕳️' expand='false'>null</fold>);
            }<fold text='' expand='false'></fold></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Equals p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
            <fold text='val' expand='false'>EqualsFull</fold> that = <fold text='' expand='false'>(EqualsFull) </fold>o;
            <fold text='🔙' expand='false'>return</fold> ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == <fold text='🕳️' expand='false'>null</fold>);
        }</fold></fold>

        public <fold text='🏛️' expand='false'>class</fold> EqualsPartial <fold text='{...}' expand='true'>{
            <fold text='@Equals L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>EqualsPartial</fold> that = <fold text='' expand='false'>(EqualsPartial) </fold>o;
                <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == <fold text='🕳️' expand='false'>null</fold>;
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> EqualsPartialTwo <fold text='{...}' expand='true'>{
            <fold text='@Equals L' expand='false'>L</fold>ombokTestData data;
            <fold text='@Equals b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>EqualsPartialTwo</fold> that = <fold text='' expand='false'>(EqualsPartialTwo) </fold>o;
                <fold text='🔙' expand='false'>return</fold> ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == <fold text='🕳️' expand='false'>null</fold>);
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@HashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> HashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            <fold text='🔙' expand='false'>return</fold> result;
        }</fold></fold>

        public <fold text='🏛️' expand='false'>class</fold> HashCodePartial <fold text='{...}' expand='true'>{
            <fold text='@HashCode L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'><fold text='' expand='true'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> HashCodePartialTwo <fold text='{...}' expand='true'>{
            <fold text='@HashCode L' expand='false'>L</fold>ombokTestData data;
            <fold text='@HashCode b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Data p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DataFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='📍' expand='false'></fold>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> DataFull)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
            <fold text='val' expand='false'>DataFull</fold> dataFull = <fold text='' expand='false'>(DataFull) </fold>o;
            <fold text='🔙' expand='false'>return</fold> new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public String toString() <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> new ToStringBuilder(<fold text='📍' expand='false'>this</fold>)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }</fold></fold>

        <fold text='@Data p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DataWithoutToString <fold text='{...}' expand='true'>{
            LombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> LombokTestData.DataFull)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                <fold text='🔙' expand='false'>return</fold> new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

        }</fold>

        <fold text='@Setter @ToString @EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DataWithPartialGetters <fold text='{...}' expand='true'>{
            <fold text='@Getter L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            <fold text='' expand='false'></fold>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> LombokTestData.DataFull)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                <fold text='🔙' expand='false'>return</fold> new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public String toString() <fold text='{...}' expand='true'>{
                <fold text='🔙' expand='false'>return</fold> new ToStringBuilder(<fold text='📍' expand='false'>this</fold>)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>

        <fold text='@Getter @ToString @EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DataWithPartialSetters <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='📍' expand='false'></fold>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> LombokTestData.DataFull)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                <fold text='🔙' expand='false'>return</fold> new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public String toString() <fold text='{...}' expand='true'>{
                <fold text='🔙' expand='false'>return</fold> new ToStringBuilder(<fold text='📍' expand='false'>this</fold>)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> FoldOn <fold text='{...}' expand='true'>{
        <fold text='@Getter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> FoldOnPublic <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@Getter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> FoldOnClass <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        @SuppressWarnings("ALL")
        <fold text='@Getter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> FoldOnWithAnnotation <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DirtyLombokGetters <fold text='{...}' expand='true'>{
        <fold text='🔘' expand='false'>boolean</fold> dirty;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> dirty2;

        public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>dirty2<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='🔘' expand='false'>boolean</fold> isDirty2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DirtyData <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Getter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> <fold text='🔘' expand='false'>boolean</fold> ok;

            public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>!dirty<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> DirtyData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> DirtySingle <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Getter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty2<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DirtyLombokSetters <fold text='{...}' expand='true'>{
        <fold text='🔘' expand='false'>boolean</fold> dirty;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> dirty2;

        public <fold text='💀' expand='false'>void</fold> setDirty(<fold text='🔘' expand='false'>boolean</fold> dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.dirty2 = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> setDirty2(<fold text='🔘' expand='false'>boolean</fold> dirty2)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.dirty = dirty2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='@Getter @EqualsAndHashCode p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> DirtyData <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Setter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> <fold text='🔘' expand='false'>boolean</fold> ok;

            public <fold text='💀' expand='false'>void</fold> setDirty(<fold text='🔘' expand='false'>boolean</fold> dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.dirty = !dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text='' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> DirtyData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            }<fold text='' expand='false'></fold></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> DirtySingle <fold text='{...}' expand='true'>{
            <fold text='🔘' expand='false'>boolean</fold> dirty;
            <fold text='@Setter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='📍' expand='false'></fold>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public <fold text='💀' expand='false'>void</fold> setDirty(<fold text='🔘' expand='false'>boolean</fold> dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LogAnnotation <fold text='{...}' expand='true'>{
        <fold text='@Log p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LogJava <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LogJava2 <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log(logger) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LogDiffrentFieldName <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='const' expand='false'><fold text='' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>Logger</fold> logger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log(xlogger) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> LogCustomNameDeprecated <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>@Deprecated
            <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>Logger</fold> xlogger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log @Log(log2) @Log(log3) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> TripleLogJava <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'><fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>Logger</fold> log2 = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
            </fold><fold text='⚡' expand='false'><fold text='' expand='false'>static</fold> Logger log3 = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>
    }</fold>


    public <fold text='🏛️' expand='false'>class</fold> Parent <fold text='{...}' expand='true'>{
        public Parent(String child) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> NoArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> NoArgsConstructor <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructor() <fold text='{}' expand='true'>{
            }</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(private) c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> NoArgsConstructorPrivate <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='🚫' expand='false'><fold text='' expand='false'>private</fold> NoArgsConstructorPrivate() <fold text='{}' expand='true'>{
            }</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(// comment) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> NoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorSuperBefore() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>// comment<fold text=' ' expand='true'>
                </fold><fold text='💪' expand='false'>super</fold>()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(// comment) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> NoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorSuperAfter() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='💪' expand='false'>super</fold>()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
                </fold>// comment<fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@NoArgsConstructor(protected,// comment) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> ProtectedNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            <fold text='🛡️' expand='false'><fold text='' expand='false'></fold>protected</fold> ProtectedNoArgsConstructorSuperAfter() <fold text='{}' expand='true'>{
                // comment
            }</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> NoArgsConstructorSuper <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorSuper()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='💪' expand='false'>super</fold>()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        public <fold text='🏛️' expand='false'>class</fold> NoArgsConstructorSuperParent extends Parent <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperParent()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='💪' expand='false'>super</fold>(<fold text='🕳️' expand='false'>null</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>

    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> AllArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>
        public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsBrokenFieldAssigmentLeft <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;
            public AllArgsBrokenFieldAssigmentLeft(<fold text='🔢' expand='false'>int</fold> field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = field1;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>
        public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsBrokenFieldAssigmentRight <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;
            public AllArgsBrokenFieldAssigmentRight(<fold text='🔢' expand='false'>int</fold> field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field1 = field2;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>

        public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsNoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                // comment
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>
        public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
                // comment
            }</fold>
        }</fold>

        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsSuper <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>

        <fold text='@AllArgsConstructor(private) p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> StaticNameArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='🚫' expand='false'><fold text='' expand='false'>private</fold> StaticNameArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
            public <fold text='⚡' expand='false'>static</fold> StaticNameArgs of(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new StaticNameArgs(field1, field2, field3)<fold text='' expand='true'>;</fold><fold text='' expand='true'>
            </fold>}</fold>
        }</fold>

        <fold text='@AllArgsConstructor(protected) p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ProtectedArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='🛡️' expand='false'><fold text='' expand='false'>protected</fold> ProtectedArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>
    }</fold>


    public <fold text='🏛️' expand='false'>class</fold> RequiredArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> RequiredArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public RequiredArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>

        public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> RequiredArgsNoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                // comment
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>

        public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> RequiredArgsNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
                // comment
            }</fold>
        }</fold>

        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> RequiredArgsSuper <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public RequiredArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>

        <fold text='@RequiredArgsConstructor(private) p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> StaticNameArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>

            </fold><fold text='🚫' expand='false'><fold text='' expand='false'>private</fold> StaticNameArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>

            public <fold text='⚡' expand='false'>static</fold> StaticNameArgs of(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new StaticNameArgs(field1, field2, field3)<fold text='' expand='true'>;</fold><fold text='' expand='true'>
            </fold>}</fold>
        }</fold>

        <fold text='@RequiredArgsConstructor(protected) p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ProtectedArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>

            </fold><fold text='🛡️' expand='false'><fold text='' expand='false'>protected</fold> ProtectedArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> ValueAnnotation <fold text='{...}' expand='true'>{
        <fold text='@Value p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ValueArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueArgs(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>o == <fold text='🕳️' expand='false'>null</fold> || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='val' expand='false'>ValueArgs</fold> valueArgs = <fold text='' expand='false'>(ValueArgs) </fold>o;
                if <fold text='' expand='false'>(</fold>field2 != valueArgs.field2<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                if <fold text='' expand='false'>(</fold>field3 != valueArgs.field3<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>field1 != null ? </fold>field1<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>valueArgs.field1<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>valueArgs.field1 == <fold text='🕳️' expand='false'>null</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = <fold text='' expand='false'>field1 != null ? </fold>field1<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0;
                result = 31 * result + field2;
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>field3 ? 1 : 0<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='true'><fold text='' expand='false'></fold>@Override</fold>
            public String toString() <fold text='{...}' expand='true'>{
                <fold text='🔙' expand='false'>return</fold> "ValueArgs{<fold text='' expand='false'>" +
                        "</fold>field1='<fold text='$' expand='false'>" + </fold>field1<fold text='${' expand='false'> + </fold>'\''<fold text='}' expand='false'> +
                        "</fold>, field2=<fold text='$' expand='false'>" + </fold>field2<fold text='' expand='false'> +
                        "</fold>, field3=<fold text='$' expand='false'>" + </fold>field3<fold text='${' expand='false'> +
                        </fold>'}'<fold text='}";' expand='false'>;</fold>
            }</fold></fold>
        }</fold>
        <fold text='@Value p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ValueArgsSuper <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                <fold text='💪' expand='false'>super</fold>();
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> ValueArgsSuper)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='val' expand='false'>ValueArgsSuper</fold> that = <fold text='' expand='false'>(ValueArgsSuper) </fold>o;
                <fold text='🔙' expand='false'>return</fold> field2 == that.field2 && field3 == that.field3 && <fold text='' expand='false'>Objects.equals(</fold>field1<fold text=' ≡ ' expand='false'>, </fold>that.field1<fold text='' expand='false'>)</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                <fold text='🔙' expand='false'>return</fold> result;
            }</fold></fold>
        }</fold>
        <fold text='@LightValue p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ValueWihhoutEqualsAndHashcode <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field2;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueWihhoutEqualsAndHashcode(String field1, <fold text='🔢' expand='false'>int</fold> field2, <fold text='🔘' expand='false'>boolean</fold> field3) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='📍' expand='false'>this</fold>.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field3<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>
    <fold text='🏛️' expand='false'>class</fold> SingleField <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> AllArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgs(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='📍' expand='false'></fold>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ReqArgs <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ReqArgs(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@Value p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Value <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public Value(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(<fold text='📍' expand='false'></fold>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
                if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> Value)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;

                <fold text='val' expand='false'>Value</fold> value = <fold text='' expand='false'>(Value) </fold>o;
                <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>Objects.equals(</fold>field1<fold text=' ≡ ' expand='false'>, </fold>value.field1<fold text='' expand='false'>)</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
            public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(field1)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@LightValue p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> ValueWithoutEqualsAndHashCode <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueWithoutEqualsAndHashCode(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='🏛️' expand='false'>class</fold> Modifers <fold text='{...}' expand='true'>{
            <fold text='@AllArgsConstructor(default) s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsDefault <fold text='{...}' expand='true'>{
                <fold text='🚫' expand='false'>private</fold> String field1;<fold text='' expand='false'>
                </fold><fold text='' expand='false'>AllArgsDefault(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
            <fold text='@AllArgsConstructor(private) s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsPrivate <fold text='{...}' expand='true'>{
                <fold text='🚫' expand='false'>private</fold> String field1;<fold text='' expand='false'>
                </fold><fold text='🚫' expand='false'><fold text='' expand='false'>private</fold> AllArgsPrivate(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
            <fold text='@AllArgsConstructor(protected) s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> <fold text='🏛️' expand='false'>class</fold> AllArgsProteced <fold text='{...}' expand='true'>{
                <fold text='🚫' expand='false'>private</fold> String field1;<fold text='' expand='false'>
                </fold><fold text='🛡️' expand='false'><fold text='' expand='false'>protected</fold> AllArgsProteced(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> FieldLevelData <fold text='{...}' expand='true'>{
        <fold text='@Data p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> String name;
        <fold text='🚫' expand='false'>private</fold> String ignored;<fold text='' expand='false'>

        <fold text='' expand='false'></fold>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setName(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.name = <fold text='<<' expand='false'>name</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> FieldLevelData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
            <fold text='val' expand='false'>FieldLevelData</fold> that = <fold text='' expand='false'>(FieldLevelData) </fold>o;
            <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>Objects.equals(</fold>name<fold text=' ≡ ' expand='false'>, </fold>that.name<fold text='' expand='false'>)</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(name)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> FieldLevelValue <fold text='{...}' expand='true'>{
        <fold text='@Value p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> <fold text='🔒' expand='false'>final</fold> String name = "1";
        <fold text='🚫' expand='false'>private</fold> String ignored;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'><fold text='' expand='true'>@Override</fold>
        public <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> FieldLevelData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
            <fold text='val' expand='false'>FieldLevelData</fold> that = <fold text='' expand='false'>(FieldLevelData) </fold>o;
            <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>Objects.equals(</fold>name<fold text=' ≡ ' expand='false'>, </fold>that.name<fold text='' expand='false'>)</fold>;
        }<fold text='' expand='false'></fold></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(name)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>
    <fold text='🏛️' expand='false'>class</fold> FieldLevelNotFinalNotValue <fold text='{...}' expand='true'>{
        <fold text='@Getter @EqualsAndHashCode p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> String name;
        <fold text='🚫' expand='false'>private</fold> String ignored;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔒' expand='false'>final</fold> <fold text='🔘' expand='false'>boolean</fold> equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='📍' expand='false'>this</fold> == o<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='✅' expand='false'>true</fold>;
            if <fold text='' expand='false'>(</fold>!(o <fold text='is' expand='false'>instanceof</fold> FieldLevelData)<fold text='' expand='false'>)</fold> <fold text='🔙' expand='false'>return</fold> <fold text='❌' expand='false'>false</fold>;
            <fold text='val' expand='false'>FieldLevelData</fold> that = <fold text='' expand='false'>(FieldLevelData) </fold>o;
            <fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>Objects.equals(</fold>name<fold text=' ≡ ' expand='false'>, </fold>that.name<fold text='' expand='false'>)</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(name)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    <fold text='@Builder c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> ClassWithBuilder <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> String name;
        <fold text='🏛️' expand='false'>class</fold> ClassWithBuilderBuilder <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String name;
            public ClassWithBuilderBuilder name(String name) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.name = <fold text='<<' expand='false'>name</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>
            public ClassWithBuilder build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new ClassWithBuilder()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

}
