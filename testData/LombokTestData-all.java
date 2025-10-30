package data;

import <fold text='...' expand='false'>org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.logging.Logger;</fold>

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.processor.lombok.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */</fold>
@SuppressWarnings("ALL")
<fold text='@HasBuilder(ClassWithBuilder) @Getter @Setter @Serial p' expand='false'>p</fold>ublic class LombokTestData {<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>long</fold> serialVersionUID = 1234567L;</fold>

    LombokTestData data;
    boolean ok;
    String string;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.string = <fold text='<<' expand='false'>string</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic class LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public class LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public class LombokSettersPartial <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class LombokSettersFinalField <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            final boolean ok = true;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@ToString p' expand='false'>p</fold>ublic class ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public String toString() <fold text='{...}' expand='true'>{
            return "ToStringFull{<fold text='' expand='false'>" +
                    "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='' expand='false'> +
                    "</fold>, ok=<fold text='$' expand='false'>" + </fold>ok<fold text='${' expand='false'> +
                    </fold>'}'<fold text='}";' expand='false'>;</fold>
        }</fold></fold>

        public class ToStringPartial <fold text='{...}' expand='true'>{
            <fold text='@ToString L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public String toString() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"ToStringPartial{<fold text='' expand='false'>" +
                        "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='${' expand='false'> +
                        </fold>'}'<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>

        public class ToStringPartial2 <fold text='{...}' expand='true'>{
            <fold text='@ToString L' expand='false'>L</fold>ombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public String toString() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"ToStringPartial{<fold text='' expand='false'>" +
                        "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='${' expand='false'> +
                        </fold>'}'<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>EqualsAndHashCodeFull</fold> that = <fold text='' expand='false'>(EqualsAndHashCodeFull) </fold>o;
            return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            return result;
        }</fold></fold>

        public class EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            <fold text='@EqualsAndHashCode L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsAndHashCodePartial</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartial) </fold>o;
                return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)<fold text=' ?: ' expand='false'></fold> : </fold>that.data == null;
            }<fold text='' expand='false'></fold></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            <fold text='@EqualsAndHashCode L' expand='false'>L</fold>ombokTestData data;
            <fold text='@EqualsAndHashCode b' expand='false'>b</fold>oolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsAndHashCodePartialTwo</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartialTwo) </fold>o;
                return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override<fold text='' expand='true'></fold>
            </fold>public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Equals p' expand='false'>p</fold>ublic class EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override<fold text='' expand='true'></fold>
        </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>EqualsFull</fold> that = <fold text='' expand='false'>(EqualsFull) </fold>o;
            return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
        }</fold></fold>

        public class EqualsPartial <fold text='{...}' expand='true'>{
            <fold text='@Equals L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsPartial</fold> that = <fold text='' expand='false'>(EqualsPartial) </fold>o;
                return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null;
            }</fold></fold>
        }</fold>

        public class EqualsPartialTwo <fold text='{...}' expand='true'>{
            <fold text='@Equals L' expand='false'>L</fold>ombokTestData data;
            <fold text='@Equals b' expand='false'>b</fold>oolean ok;
            String string;<fold text='' expand='false'>

            <fold text='' expand='true'><fold text='' expand='false'></fold>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsPartialTwo</fold> that = <fold text='' expand='false'>(EqualsPartialTwo) </fold>o;
                return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@HashCode p' expand='false'>p</fold>ublic class HashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            return result;
        }</fold></fold>

        public class HashCodePartial <fold text='{...}' expand='true'>{
            <fold text='@HashCode L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold><fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class HashCodePartialTwo <fold text='{...}' expand='true'>{
            <fold text='@HashCode L' expand='false'>L</fold>ombokTestData data;
            <fold text='@HashCode b' expand='false'>b</fold>oolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override<fold text='' expand='true'></fold>
            </fold>public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Data p' expand='false'>p</fold>ublic class DataFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>!(o instanceof DataFull)<fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>DataFull</fold> dataFull = <fold text='' expand='false'>(DataFull) </fold>o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public String toString() <fold text='{...}' expand='true'>{
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }</fold></fold>

        <fold text='@Data p' expand='false'>p</fold>ublic class DataWithoutToString <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

        }</fold>

        <fold text='@Setter @ToString @EqualsAndHashCode p' expand='false'>p</fold>ublic class DataWithPartialGetters <fold text='{...}' expand='true'>{
            <fold text='@Getter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = <fold text='<<' expand='false'>ok<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>

        <fold text='@Getter @ToString @EqualsAndHashCode p' expand='false'>p</fold>ublic class DataWithPartialSetters <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold></fold>
        }</fold>
    }</fold>

    public class FoldOn <fold text='{...}' expand='true'>{
        <fold text='@Getter p' expand='false'>p</fold>ublic class FoldOnPublic <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@Getter c' expand='false'>c</fold>lass FoldOnClass <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        @SuppressWarnings("ALL")
        <fold text='@Getter c' expand='false'>c</fold>lass FoldOnWithAnnotation <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    public class DirtyLombokGetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public boolean isDirty2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Getter p' expand='false'>p</fold>rivate boolean ok;

            public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>!dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof DirtyData)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> return false;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> return false;

                return true;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            <fold text='' expand='false'></fold>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty2<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold>
        }</fold>
    }</fold>

    public class SupportedDirtyLombokGetters <fold text='{...}' expand='true'>{
        private List<String> wrapper;
        private List<String> wrapperWrongRef;
        private List<String> wrapperDeeplyHiddenRef;

        private List<String> localMethodWrappedList;
        private List<String> thisLocalMethodWrappedList;
        private List<String> lazyLoadedList;
        private List<String> oneLineLazyLoadedList;
        private List<String> defensiveCopyList;

        public List<String> getWrapper()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Collections.unmodifiableList(wrapper)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<String> getWrapperDeeplyHiddenRef() <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>wrapper != null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='@SneakyThrows' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='true'>    </fold>return wrapperDeeplyHiddenRef;<fold text='' expand='true'>
                </fold><fold text='' expand='true'>}</fold></fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    throw new RuntimeException(e);
                }</fold></fold>
            }</fold>
            return null;
        }</fold>

        public List<String> getWrapperWrongRef()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Collections.unmodifiableList(wrapper)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<String> getLocalMethodWrappedList()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>localWrap(localMethodWrappedList)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<String> getThisLocalMethodWrappedList()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.localWrap(thisLocalMethodWrappedList)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<String> getLazyLoadedList() <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>lazyLoadedList == null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                lazyLoadedList = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
            }</fold>
            return lazyLoadedList;
        }</fold>

        public List<String> getDefensiveCopyList()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new ArrayList<>(defensiveCopyList)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<String> getOneLineLazyLoadedList() <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>oneLineLazyLoadedList == null<fold text='' expand='false'>)</fold> oneLineLazyLoadedList = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
            return oneLineLazyLoadedList;
        }</fold>

        private List<String> localWrap(List<String> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class SupportedDirtyLombokSetters <fold text='{...}' expand='true'>{
        private List<String> setterWrapper;
        private List<String> setterLocalWrapper;

        public void setSetterWrapper(List<String> setterWrapper) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>this.setterWrapper = Collections.unmodifiableList(<fold text='<<' expand='false'>setterWrapper</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
        </fold>}</fold>

        public void setSetterLocalWrapper(List<String> setterLocalWrapper) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>this.setterLocalWrapper = this.localWrap(<fold text='<<' expand='false'>setterLocalWrapper</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
        </fold>}</fold>

        private List<String> localWrap(List<String> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>list<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class DirtyLombokSetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;
        <fold text='@Setter b' expand='false'>b</fold>oolean withoutThis;

        public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.dirty2 = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void setDirty2(boolean dirty2)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.dirty = dirty2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setWithoutThis(boolean withoutThiss)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>withoutThis = withoutThiss<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        <fold text='@Getter @EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Setter p' expand='false'>p</fold>rivate boolean ok;

            public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.dirty = !dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'><fold text='' expand='true'>@Override<fold text='' expand='true'></fold>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof DirtyData)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> return false;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> return false;

                return true;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Setter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    public class LogAnnotation <fold text='{...}' expand='true'>{
        <fold text='@Log p' expand='false'>p</fold>ublic class LogJava <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log p' expand='false'>p</fold>ublic class LogJava2 <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log(logger) p' expand='false'>p</fold>ublic class LogDiffrentFieldName <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='const' expand='false'><fold text='' expand='false'>public static final </fold><fold text='' expand='false'>Logger</fold> logger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log(xlogger) p' expand='false'>p</fold>ublic class LogCustomNameDeprecated <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>@Deprecated
            <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>Logger</fold> xlogger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log @Log(log2) @Log(log3) p' expand='false'>p</fold>ublic class TripleLogJava <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
            </fold><fold text='const' expand='false'><fold text='' expand='false'>public static final </fold><fold text='' expand='false'>Logger</fold> log2 = Logger.getLogger("LogAnnotation.class");<fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>static Logger log3 = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>
    }</fold>


    public class Parent <fold text='{...}' expand='true'>{
        public Parent(String child) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public class NoArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic class NoArgsConstructor <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructor() <fold text='{}' expand='true'>{
            }</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(private) c' expand='false'>c</fold>lass NoArgsConstructorPrivate <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            <fold text='' expand='false'></fold>private NoArgsConstructorPrivate() <fold text='{}' expand='true'>{
            }</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(// comment) p' expand='false'>p</fold>ublic class NoArgsConstructorCommentBeforeSuper <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorCommentBeforeSuper() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>// comment<fold text=' ' expand='true'>
                </fold>super()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(// comment) p' expand='false'>p</fold>ublic class NoArgsConstructorCommentAfterSuper <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorCommentAfterSuper() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>super()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
                </fold>// comment<fold text=' ' expand='true'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor(protected,// comment) p' expand='false'>p</fold>ublic class ProtectedNoArgsConstructorComment <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>protected ProtectedNoArgsConstructorComment() <fold text='{}' expand='true'>{
                // comment
            }</fold></fold>
        }</fold>
        // comment hidden as it is common and adds no meaningful value
        <fold text='@NoArgsConstructor(private) p' expand='false'>p</fold>ublic class PrivateNoArgsConstructorComment <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>private PrivateNoArgsConstructorComment() <fold text='{}' expand='true'>{
                // comment
            }</fold></fold>
        }</fold>
        <fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic class NoArgsConstructorSuper <fold text='{...}' expand='true'>{
            private String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorSuper()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>super()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        public class NoArgsConstructorSuperParent extends Paren<fold text='t(nothing overridden)' expand='true'>t</fold> <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperParent()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>super(null)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>

    }</fold>

    public class AllArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic static class AllArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>
        public static class AllArgsBrokenFieldAssigmentLeft <fold text='{...}' expand='true'>{
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = field1;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>
        public static class AllArgsBrokenFieldAssigmentRight <fold text='{...}' expand='true'>{
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field1 = field2;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>

        public static class AllArgsNoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                // comment
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>
        public static class AllArgsNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
                // comment
            }</fold>
        }</fold>

        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic static class AllArgsSuper <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>

        <fold text='@AllArgsConstructor(private) p' expand='false'>p</fold>ublic static class StaticNameArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>private StaticNameArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
            public static StaticNameArgs of(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                <fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>new StaticNameArgs(field1, field2, field3)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
            </fold>}</fold>
        }</fold>

        <fold text='@AllArgsConstructor(protected) p' expand='false'>p</fold>ublic static class ProtectedArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>protected ProtectedArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>
    }</fold>


    public class RequiredArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic static class RequiredArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public RequiredArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>

        public static class RequiredArgsNoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                // comment
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold>
        }</fold>

        public static class RequiredArgsNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
                // comment
            }</fold>
        }</fold>

        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic static class RequiredArgsSuper <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public RequiredArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>

        <fold text='@RequiredArgsConstructor(private) p' expand='false'>p</fold>ublic static class StaticNameArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>private StaticNameArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>

            public static StaticNameArgs of(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new StaticNameArgs(field1, field2, field3)<fold text='' expand='true'>;<fold text=' ' expand='true'></fold>
            </fold>}</fold>
        }</fold>

        <fold text='@RequiredArgsConstructor(protected) p' expand='false'>p</fold>ublic static class ProtectedArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>protected ProtectedArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold>
        }</fold>
    }</fold>

    public class ValueAnnotation <fold text='{...}' expand='true'>{
        <fold text='@Value p' expand='false'>p</fold>ublic static class ValueArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>
            <fold text='' expand='false'></fold>public ValueArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field3<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>ValueArgs</fold> valueArgs = <fold text='' expand='false'>(ValueArgs) </fold>o;
                if <fold text='' expand='false'>(</fold>field2 != valueArgs.field2<fold text='' expand='false'>)</fold> return false;
                if <fold text='' expand='false'>(</fold>field3 != valueArgs.field3<fold text='' expand='false'>)</fold> return false;
                return <fold text='' expand='false'>field1 != null ? </fold>field1<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>valueArgs.field1<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>valueArgs.field1 == null;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = <fold text='' expand='false'>field1 != null ? </fold>field1<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0;
                result = 31 * result + field2;
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>field3 ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public String toString() <fold text='{...}' expand='true'>{
                return "ValueArgs{<fold text='' expand='false'>" +
                        "</fold>field1='<fold text='$' expand='false'>" + </fold>field1<fold text='${' expand='false'> + </fold>'\''<fold text='}' expand='false'> +
                        "</fold>, field2=<fold text='$' expand='false'>" + </fold>field2<fold text='' expand='false'> +
                        "</fold>, field3=<fold text='$' expand='false'>" + </fold>field3<fold text='${' expand='false'> +
                        </fold>'}'<fold text='}";' expand='false'>;</fold>
            }</fold></fold>
        }</fold>
        <fold text='@Value p' expand='false'>p</fold>ublic static class ValueArgsSuper <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override<fold text='' expand='true'></fold>
            </fold>public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof ValueArgsSuper)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>ValueArgsSuper</fold> that = <fold text='' expand='false'>(ValueArgsSuper) </fold>o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
            </fold>public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }</fold></fold>
        }</fold>
        <fold text='@LightValue p' expand='false'>p</fold>ublic static class ValueWihhoutEqualsAndHashcode <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>
    class SingleField <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic static class AllArgs <fold text='{...}' expand='true'>{
            private String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgs(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic static class ReqArgs <fold text='{...}' expand='true'>{
            private final String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ReqArgs(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@Value p' expand='false'>p</fold>ublic static class Value <fold text='{...}' expand='true'>{
            private final String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public Value(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>
            <fold text='' expand='true'><fold text='' expand='false'></fold>@Override</fold><fold text='' expand='true'>
            </fold>public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof Value)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>Value</fold> value = <fold text='' expand='false'>(Value) </fold>o;
                return Objects.equals(field1, value.field1);
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>@Override<fold text='' expand='true'></fold>
            </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(field1)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@LightValue p' expand='false'>p</fold>ublic static class ValueWithoutEqualsAndHashCode <fold text='{...}' expand='true'>{
            private final String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueWithoutEqualsAndHashCode(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold></fold>
        }</fold>
        class Modifers <fold text='{...}' expand='true'>{
            <fold text='@AllArgsConstructor(default) s' expand='false'>s</fold>tatic class AllArgsDefault <fold text='{...}' expand='true'>{
                private String field1;<fold text='' expand='false'>
                </fold><fold text='' expand='false'>AllArgsDefault(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
            <fold text='@AllArgsConstructor(private) s' expand='false'>s</fold>tatic class AllArgsPrivate <fold text='{...}' expand='true'>{
                private String field1;<fold text='' expand='false'>
                </fold><fold text='' expand='false'>private AllArgsPrivate(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
            <fold text='@AllArgsConstructor(protected) s' expand='false'>s</fold>tatic class AllArgsProteced <fold text='{...}' expand='true'>{
                private String field1;<fold text='' expand='false'>
                <fold text='' expand='false'></fold>protected AllArgsProteced(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
        }</fold>
    }</fold>

    class FieldLevelData <fold text='{...}' expand='true'>{
        <fold text='@Data p' expand='false'>p</fold>rivate String name;
        private String ignored;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setName(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.name = <fold text='<<' expand='false'>name</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>!(o instanceof FieldLevelData)<fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>FieldLevelData</fold> that = <fold text='' expand='false'>(FieldLevelData) </fold>o;
            return Objects.equals(name, that.name);
        }</fold></fold><fold text='' expand='false'>

        <fold text='' expand='true'><fold text='' expand='false'></fold>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(name)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class FieldLevelValue <fold text='{...}' expand='true'>{
        <fold text='@Value p' expand='false'>p</fold>rivate final String name = "1";
        private String ignored;<fold text='' expand='false'>

        <fold text='' expand='false'></fold>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>!(o instanceof FieldLevelData)<fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>FieldLevelData</fold> that = <fold text='' expand='false'>(FieldLevelData) </fold>o;
            return Objects.equals(name, that.name);
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(name)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>
    class FieldLevelNotFinalNotValue <fold text='{...}' expand='true'>{
        <fold text='@Getter @EqualsAndHashCode p' expand='false'>p</fold>rivate String name;
        private String ignored;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>!(o instanceof FieldLevelData)<fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>FieldLevelData</fold> that = <fold text='' expand='false'>(FieldLevelData) </fold>o;
            return Objects.equals(name, that.name);
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(name)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    <fold text='@HasBuilder c' expand='false'>c</fold>lass ClassWithBuilder <fold text='{...}' expand='true'>{
        private String name;
        class ClassWithBuilderBuilder <fold text='{...}' expand='true'>{
            private String name;
            public ClassWithBuilderBuilder name(String name) <fold text='{...}' expand='true'>{
                this.name = <fold text='<<' expand='false'>name</fold>;
                return this;
            }</fold>
            public ClassWithBuilder build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new ClassWithBuilder()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    class Constructors <fold text='{...}' expand='true'>{
        <fold text='@NoArgsConstructor @AllArgsConstructor p' expand='false'>p</fold>ublic class FiveConstructors <fold text='{...}' expand='true'>{
            <fold text='@Constructor(2-1) @Constructor(3-1) @Constructor(4-1) p' expand='false'>p</fold>rivate int field1;
            <fold text='@Constructor(3-2) @Constructor(4-2) p' expand='false'>p</fold>rivate String field2;
            <fold text='@Constructor(4-3) p' expand='false'>p</fold>rivate double field3;
            private boolean field4;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public FiveConstructors() <fold text='{}' expand='true'>{
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public FiveConstructors(int field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public FiveConstructors(int field1, String field2) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public FiveConstructors(int field1, String field2, double field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public FiveConstructors(int field1, String field2, double field3, boolean field4) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
                this.field4 = <fold text='<<' expand='false'>field4</fold>;
            }</fold></fold>
        }</fold>

        <fold text='@NoArgsConstructor @AllArgsConstructor p' expand='false'>p</fold>ublic class FiveConstructorsMod <fold text='{...}' expand='true'>{
            <fold text='@Constructor(2-1,private) @Constructor(3-1,default) @Constructor(4-1,protected) p' expand='false'>p</fold>rivate int field1;
            <fold text='@Constructor(3-2,default) @Constructor(4-2,protected) p' expand='false'>p</fold>rivate String field2;
            <fold text='@Constructor(4-3,protected) p' expand='false'>p</fold>rivate double field3;
            private boolean field4;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public FiveConstructorsMod() <fold text='{}' expand='true'>{
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>private FiveConstructorsMod(int field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>FiveConstructorsMod(int field1, String field2) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>protected FiveConstructorsMod(int field1, String field2, double field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
                this.field4 = <fold text='<<' expand='false'>field4</fold>;
            }</fold></fold>
        }</fold>

        class SingleConstructor <fold text='{...}' expand='true'>{
            <fold text='@Constructor(default) p' expand='false'>p</fold>rivate final int field1;
            private int field2;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>SingleConstructor(int field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        private class SingleDoubleConstructor <fold text='{...}' expand='true'>{
            <fold text='@Constructor(1,private) p' expand='false'>p</fold>rivate final int field1;
            <fold text='@Constructor(2,private) p' expand='false'>p</fold>rivate final int field2;
            private int field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>private SingleDoubleConstructor(int field1, int field2) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
            }</fold></fold>
        }</fold>

        public class SingleConstructorNoMod <fold text='{...}' expand='true'>{
            <fold text='@Constructor p' expand='false'>p</fold>rivate final int field1;
            private int field2;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public SingleConstructorNoMod(int field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = <fold text='<<' expand='false'>field1</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class SingleDoubleConstructorNoMod <fold text='{...}' expand='true'>{
            <fold text='@Constructor(1) p' expand='false'>p</fold>rivate final int field1;
            <fold text='@Constructor(2) p' expand='false'>p</fold>rivate final int field2;
            private int field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public SingleDoubleConstructorNoMod(int field1, int field2) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
            }</fold></fold>
        }</fold>

    }</fold>

    <fold text='@HasBuilder(FirstBuilder) @HasBuilder(SecondBuilder) @HasBuilder c' expand='false'>c</fold>lass Builders <fold text='{...}' expand='true'>{

        class FirstBuilder <fold text='{...}' expand='true'>{

        }</fold>

        class SecondBuilder <fold text='{...}' expand='true'>{

        }</fold>

        class BuildersBuilder <fold text='{...}' expand='true'>{

        }</fold>

    }</fold>

}
