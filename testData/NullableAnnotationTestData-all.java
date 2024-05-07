package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;</fold>

<fold text='@Getter @Setter* @Serial @' expand='false'>@</fold>SuppressWarnings("ALL")
public class NullableAnnotationTestData {<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private static final long serialVersionUID = 1234567L;</fold>
    @NotNull
    NullableAnnotationTestData data;
    boolean ok;
    @Nullable
    String string;<fold text='' expand='false'>
    </fold><fold text='' expand='false'>public NullableAnnotationTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public void setData(NullableAnnotationTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = <fold text='<<' expand='false'>string</fold>;<fold text=' }' expand='false'>
    }</fold></fold>

    @Nonnull
    private NullableAnnotationTestData data2;
    boolean ok2;
    @Nullable
    private String string2;

    public void select(<fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>element,
                       int i,
                       <fold text='' expand='false'>@NotNull</fold> Object<fold text='!! ' expand='false'> </fold>o,
                       <fold text='' expand='false'>@Nonnull</fold> LocalDate<fold text='!! ' expand='false'> </fold>date
    ) <fold text='{}' expand='true'>{

    }</fold>

    <fold text='' expand='false'>@NotNull</fold>
    public String<fold text='!! ' expand='false'> </fold>getStringNotNull()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold>

    <fold text='' expand='false'>@Nonnull</fold>
    public String<fold text='!! ' expand='false'> </fold>getStringNotNull2()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold>

    <fold text='' expand='false'>@Nullable</fold>
    public String<fold text='? ' expand='false'> </fold>getStringNull()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold>

    interface Datable <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>@Nullable</fold>
        public Integer<fold text='? ' expand='false'> </fold>select(<fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>element,
                              int i,
                              <fold text='' expand='false'>@NotNull</fold> Object<fold text='!! ' expand='false'> </fold>o,
                              <fold text='' expand='false'>@Nonnull</fold> LocalDate<fold text='!! ' expand='false'> </fold>date
        );
    }</fold>

    public enum FieldFoldingAnnotation <fold text='{...}' expand='true'>{
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        private String[] annotations;

        FieldFoldingAnnotation(String... annotations) <fold text='{}' expand='true'>{

        }</fold>

        <fold text='' expand='false'>@Nonnull</fold>
        public static int<fold text='!! ' expand='false'> </fold>select(<fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>element,
                                 int i,
                                 <fold text='' expand='false'>@NotNull</fold> Object<fold text='!! ' expand='false'> </fold>o,
                                 <fold text='' expand='false'>@Nonnull</fold> LocalDate<fold text='!! ' expand='false'> </fold>date
        ) <fold text='{...}' expand='true'>{
            return 1;
        }</fold>

    }</fold>

    public record UserDataRecord(<fold text='' expand='false'>@Nonnull</fold> String<fold text='!! ' expand='false'> </fold>username, boolean active, <fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>userIdentifier, <fold text='' expand='false'>@NotNull</fold> String<fold text='!! ' expand='false'> </fold>username2) <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@Getter c' expand='false'>c</fold>lass GetterNullable <fold text='{...}' expand='true'>{
        NullableAnnotationTestData<fold text='? ' expand='false'> </fold>getterNullable;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Nullable
        public NullableAnnotationTestData getGetterNullable()<fold text=' { ' expand='false'> {
            </fold>return getterNullable;<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='@Setter c' expand='false'>c</fold>lass SetterNullable <fold text='{...}' expand='true'>{
        NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>setterNullable;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setSetterNullable(<fold text='' expand='false'>@Nonnull</fold> NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>setterNullable) <fold text='{...}' expand='true'>{
            this.setterNullable = <fold text='<<' expand='false'>setterNullable</fold>;
        }</fold></fold>
    }</fold>


    static class ConstClass <fold text='{...}' expand='true'>{
        boolean ok;
        String string;

        public ConstClass() <fold text='{}' expand='true'>{
        }</fold>

        public ConstClass(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
        }</fold>

        public ConstClass(boolean ok, String string) <fold text='{...}' expand='true'>{
            this.ok = <fold text='<<' expand='false'>ok</fold>;
            this.string = <fold text='<<' expand='false'>string</fold>;
        }</fold>
    }</fold>
    static class SubConstClass extends ConstClass <fold text='{...}' expand='true'>{

    }</fold>
    static class Const <fold text='{...}' expand='true'>{
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF = <fold text='::new' expand='false'>new ConstClass()</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_ANN = <fold text='::new{}' expand='false'>new ConstClass() <fold text='{...}' expand='true'>{
        }</fold></fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB = new SubConstClass();
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_1 = <fold text='::new(true)' expand='false'>new ConstClass(true)</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_2 = <fold text='::new(false, "1")' expand='false'>new ConstClass(false, "1")</fold>;


        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            int i = 1;
        }</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public void setOk(boolean ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        <fold text='const' expand='false'>public static final </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
        <fold text='const' expand='false'>private static final </fold><fold text='' expand='false'>String</fold> PRIVATE_STATIC_FINAL_VAR = "";
        <fold text='const' expand='false'>protected static final </fold><fold text='' expand='false'>String</fold> PROTECTED_STATIC_FINAL_VAR = "";
        <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>String</fold> DEFAULT_STATIC_FINAL_VAR = "";
        <fold text='const' expand='false'>static final</fold> String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>int</fold> VERSION = 1;
        <fold text='const' expand='false'>static final</fold> int VERSION_REF = VERSION;
    }</fold>

}
