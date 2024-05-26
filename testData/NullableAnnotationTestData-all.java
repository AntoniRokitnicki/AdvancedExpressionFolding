package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;</fold>

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.extension.NullableExt#createExpression(com.intellij.psi.PsiMethod)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testNullableAnnotationTestData()}
 */</fold>
@SuppressWarnings("ALL")
<fold text='@Serial p' expand='false'>p</fold>ublic class NullableAnnotationTestData {<fold text='' expand='false'>

    </fold><fold text='const' expand='false'><fold text='' expand='false'>private static final </fold><fold text='' expand='false'>long</fold> serialVersionUID = 1234567L;</fold>
    <fold text='' expand='false'>@NotNull</fold>
    <fold text='@Getter @Setter N' expand='false'>N</fold>ullableAnnotationTestData<fold text='!! ' expand='false'> </fold>data;
    <fold text='@Getter @Setter b' expand='false'>b</fold>oolean ok;
    <fold text='' expand='false'>@Nullable</fold>
    <fold text='@Getter @Setter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>string;<fold text='' expand='false'>
    </fold><fold text='' expand='false'>public NullableAnnotationTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public void setData(NullableAnnotationTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>
    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>
    </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.string = string<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    <fold text='' expand='false'>@Nonnull</fold>
    private NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>data2;
    boolean ok2;
    <fold text='' expand='false'>@Nullable</fold>
    private String<fold text='? ' expand='false'> </fold>string2;

    public void select(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>element,
                       int i,
                       <fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>Object<fold text='!! ' expand='false'> </fold>o,
                       <fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>LocalDate<fold text='!! ' expand='false'> </fold>date
                       ) <fold text='{}' expand='true'>{

    }</fold>

    <fold text='' expand='false'>@NotNull</fold>
    public String<fold text='!! ' expand='false'> </fold>getStringNotNull()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='false'>@Nonnull</fold>
    public String<fold text='!! ' expand='false'> </fold>getStringNotNull2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='false'>@Nullable</fold>
    public String<fold text='? ' expand='false'> </fold>getStringNull()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    interface Datable <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>@Nullable</fold>
        public Integer<fold text='? ' expand='false'> </fold>select(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>element,
                           int i,
                           <fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>Object<fold text='!! ' expand='false'> </fold>o,
                           <fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>LocalDate<fold text='!! ' expand='false'> </fold>date
        );
    }</fold>

    <fold text='@AllArgsConstructor(private) p' expand='false'>p</fold>ublic enum FieldFoldingAnnotation <fold text='{...}' expand='true'>{
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        private String[] annotations;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>FieldFoldingAnnotation(String... annotations) <fold text='{}' expand='true'>{

        }</fold></fold>

        <fold text='' expand='false'>@Nonnull</fold>
        public static int<fold text='!! ' expand='false'> </fold>select(@Nullable String element,
                                 int i,
                                 @NotNull Object o,
                                 @Nonnull LocalDate date
        ) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>1<fold text=' ' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold>

    }</fold>

    public record UserDataRecord(<fold text='' expand='false'>@Nonnull</fold><fold text='' expand='false'> </fold>String<fold text='!! ' expand='false'> </fold>username, boolean active, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>userIdentifier, <fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>String<fold text='!! ' expand='false'> </fold>username2) <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@Getter c' expand='false'>c</fold>lass GetterNullable <fold text='{...}' expand='true'>{
        NullableAnnotationTestData getterNullable;<fold text='' expand='false'>

        </fold><fold text='' expand='false'><fold text='' expand='false'>@Nullable</fold>
        public NullableAnnotationTestData<fold text='? ' expand='false'> </fold>getGetterNullable()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>getterNullable<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    <fold text='@Setter c' expand='false'>c</fold>lass SetterNullable <fold text='{...}' expand='true'>{
        NullableAnnotationTestData setterNullable;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setSetterNullable(@Nonnull NullableAnnotationTestData setterNullable) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>this.setterNullable = setterNullable<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
        </fold>}</fold></fold>
    }</fold>

    public class LombokFieldLevelIntegration <fold text='{...}' expand='true'>{
        public class HasGetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter p' expand='false'>p</fold>rivate String<fold text='? ' expand='false'> </fold>field;
            private String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class HasSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Setter p' expand='false'>p</fold>rivate String<fold text='? ' expand='false'> </fold>field;
            private String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setField(@Nullable String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field = field<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class HasGetterSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter @Setter p' expand='false'>p</fold>rivate String<fold text='? ' expand='false'> </fold>field;
            private String bla;<fold text='' expand='false'>

            <fold text='' expand='false'></fold>public <fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field<fold text=' ' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setField(@Nullable String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field = field<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    public class LombokFieldLevelNotPrivateIntegration <fold text='{...}' expand='true'>{
        public class HasGetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>field;
            String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class HasSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Setter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>field;
            String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setField(@Nullable String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field = field<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>

        public class HasGetterSetter <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@Nullable</fold>
            <fold text='@Getter @Setter S' expand='false'>S</fold>tring<fold text='? ' expand='false'> </fold>field;
            String bla;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='' expand='false'>@Nullable</fold> String<fold text='? ' expand='false'> </fold>getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setField(@Nullable String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field = field<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>


}
