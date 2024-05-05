package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;</fold>

<fold text='@Getter @Setter* @Serial @' expand='false'>@</fold>SuppressWarnings("ALL")
public class NullableAnnotationTestData {<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private static final long serialVersionUID = 1234567L;</fold>
    <fold text='' expand='false'>@NotNull</fold>
    NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>data;
    boolean ok;
    <fold text='' expand='false'>@Nullable</fold>
    String<fold text='? ' expand='false'> </fold>string;<fold text='' expand='false'>
    </fold><fold text='' expand='false'>public NullableAnnotationTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>
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

    <fold text='' expand='false'>@Nonnull</fold>
    private NullableAnnotationTestData<fold text='!! ' expand='false'> </fold>data2;
    boolean ok2;
    <fold text='' expand='false'>@Nullable</fold>
    private String<fold text='? ' expand='false'> </fold>string2;

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

    public record UserDataRecord(@Nonnull String username, boolean active, @Nullable String userIdentifier, @NotNull String username2) <fold text='{...}' expand='true'>{
    }</fold>

}
