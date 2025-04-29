<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;
<fold text='🚢' expand='false'>import</fold> org.jetbrains.annotations.Nullable;

<fold text='🚢' expand='false'>import</fold> javax.annotation.Nonnull;
<fold text='🚢' expand='false'>import</fold> java.time.LocalDate;
<fold text='🚢' expand='false'>import</fold> java.util.HashMap;</fold>

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.extension.NullableExt#createExpression(com.intellij.psi.PsiMethod)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testNullableAnnotationTestData()}
 */</fold>
@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> NullableAnnotationTestData {
    <fold text='' expand='false'>@NotNull</fold>
    <fold text='@Getter @Setter N' expand='false'>N</fold>ullableAnnotationTestData<fold text='!! ' expand='false'> </fold>data;
    <fold text='@Getter @Setter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;
    <fold text='' expand='false'>@Nullable</fold>
    <fold text='@Getter @Setter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>string;<fold text='' expand='false'>
    </fold><fold text='' expand='false'>public NullableAnnotationTestData getData()<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(NullableAnnotationTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> ok;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> string;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = <fold text='<<' expand='false'>string</fold>;<fold text=' }' expand='false'>
    }</fold></fold>

    <fold text='' expand='false'>@Nonnull</fold>
    <fold text='🚫' expand='false'>private</fold> NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>data2;
    <fold text='🔘' expand='false'>boolean</fold> ok2;
    <fold text='' expand='false'>@Nullable</fold>
    <fold text='🚫' expand='false'>private</fold> String<fold text='? ' expand='false'> </fold>string2;

    public <fold text='💀' expand='false'>void</fold> select(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>element,
                       int i,
                       <fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>Object<fold text='!! ' expand='false'> </fold>o,
                       <fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>LocalDate<fold text='!! ' expand='false'> </fold>date
                       ) <fold text='{...}' expand='true'>{
        new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;

    }</fold>

    @NotNull
    public String getStringNotNull()<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> string;<fold text=' }' expand='false'>
    }</fold>

    @Nonnull
    public String getStringNotNull2()<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> string;<fold text=' }' expand='false'>
    }</fold>

    @Nullable
    public String getStringNull()<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> string;<fold text=' }' expand='false'>
    }</fold>

    <fold text='🖥️' expand='false'>interface</fold> Datable <fold text='{...}' expand='true'>{
        @Nullable
        public Integer select(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>element,
                           <fold text='🔢' expand='false'>int</fold> i,
                           <fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>Object<fold text='!! ' expand='false'> </fold>o,
                           <fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>LocalDate<fold text='!! ' expand='false'> </fold>date
        );
    }</fold>

    public <fold text='📊' expand='false'>enum</fold> FieldFoldingAnnotation <fold text='{...}' expand='true'>{
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        <fold text='🚫' expand='false'>private</fold> String[] annotations;

        FieldFoldingAnnotation(String... annotations) <fold text='{}' expand='true'>{

        }</fold>

        @Nonnull
        public <fold text='⚡' expand='false'>static</fold> <fold text='🔢' expand='false'>int</fold> select(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>element,
                                 <fold text='🔢' expand='false'>int</fold> i,
                                 <fold text='' expand='false'>@NotNull<fold text='' expand='false'></fold> </fold>Object<fold text='!! ' expand='false'> </fold>o,
                                 <fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>LocalDate<fold text='!! ' expand='false'> </fold>date
        ) <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> 1;
        }</fold>

    }</fold>

    public <fold text='📀' expand='false'>record</fold> UserDataRecord(<fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>String<fold text='!! ' expand='false'> </fold>username, <fold text='🔘' expand='false'>boolean</fold> active, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>userIdentifier, <fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>String<fold text='!! ' expand='false'> </fold>username2) <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@Getter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> GetterNullable <fold text='{...}' expand='true'>{
        NullableAnnotationTestData getterNullable;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Nullable
        public NullableAnnotationTestData getGetterNullable()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> getterNullable;<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='@Setter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> SetterNullable <fold text='{...}' expand='true'>{
        NullableAnnotationTestData setterNullable;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setSetterNullable(<fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>setterNullable) <fold text='{...}' expand='true'>{
            this.setterNullable = <fold text='<<' expand='false'>setterNullable</fold>;
        }</fold></fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LombokFieldLevelIntegration <fold text='{...}' expand='true'>{
        public <fold text='🏛️' expand='false'>class</fold> HasGetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> String<fold text='? ' expand='false'> </fold>field;
            <fold text='🚫' expand='false'>private</fold> String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public @Nullable String getField()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> field;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> HasSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Setter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> String<fold text='? ' expand='false'> </fold>field;
            <fold text='🚫' expand='false'>private</fold> String bla;<fold text='' expand='false'>

            <fold text='' expand='false'></fold>public <fold text='💀' expand='false'>void</fold> setField(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>field)<fold text=' { ' expand='false'> {
                </fold>this.field = <fold text='<<' expand='false'>field</fold>;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> HasGetterSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Setter p' expand='false'><fold text='🚫' expand='false'>p</fold>rivate</fold> String<fold text='? ' expand='false'> </fold>field;
            <fold text='🚫' expand='false'>private</fold> String bla;

            public @Nullable String getField() <fold text='{...}' expand='true'>{
                new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
                <fold text='🔙' expand='false'>return</fold> field;
            }</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setField(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>field)<fold text=' { ' expand='false'> {
                </fold>this.field = <fold text='<<' expand='false'>field</fold>;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LombokFieldLevelNotPrivateIntegration <fold text='{...}' expand='true'>{
        public <fold text='🏛️' expand='false'>class</fold> HasGetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>field;
            String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public @Nullable String getField()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> field;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> HasSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Setter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>field;
            String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setField(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>field)<fold text=' { ' expand='false'> {
                </fold>this.field = <fold text='<<' expand='false'>field</fold>;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> HasGetterSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter @Setter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>field;
            String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public @Nullable String getField()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> field;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setField(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>field)<fold text=' { ' expand='false'> {
                </fold>this.field = <fold text='<<' expand='false'>field</fold>;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>


}
