package data;

import <fold text='...' expand='false'>org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;</fold>

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getLombok()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.extension.lombok.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */</fold>
@SuppressWarnings("ALL")
<fold text='@Builder @Getter @Setter @Serial p' expand='false'>p</fold>ublic class LombokTestData {<fold text='' expand='false'>

    </fold><fold text='const' expand='false'><fold text='' expand='false'>private static final </fold><fold text='' expand='false'>long</fold> serialVersionUID = 1234567L;</fold>

    LombokTestData data;
    boolean ok;
    String string;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.string = string<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>Optional.ofNullable(data)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic class LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public class LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public class LombokSettersPartial <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        public class LombokSettersFinalField <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            final boolean ok = true;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@ToString p' expand='false'>p</fold>ublic class ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return "ToStringFull{<fold text='' expand='false'>" +
                    "</fold>data=<fold text='$' expand='false'>" + </fold>data<fold text='' expand='false'> +
                    "</fold>, ok=<fold text='$' expand='false'>" + </fold>ok<fold text='${' expand='false'> +
                    </fold>'}'<fold text='}";' expand='false'>;</fold>
        }</fold></fold>

        <fold text='@ToStringˣ p' expand='false'>p</fold>ublic class ToStringPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"ToStringPartial{" +
                        "data=" + data +
                        '}'<fold text=' ' expand='true'>;</fold><fold text='' expand='true'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@ToStringˣ p' expand='false'>p</fold>ublic class ToStringPartial2 <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"ToStringPartial{" +
                        "data=" + data +
                        '}'<fold text=' ' expand='true'>;</fold><fold text='' expand='true'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>EqualsAndHashCodeFull</fold> that = <fold text='' expand='false'>(EqualsAndHashCodeFull) </fold>o;
            return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            return result;
        }</fold></fold>

        <fold text='@EqualsAndHashCodeˣ p' expand='false'>p</fold>ublic class EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsAndHashCodePartial</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartial) </fold>o;
                return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data != null ? data.hashCode() : 0<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@EqualsAndHashCodeˣ p' expand='false'>p</fold>ublic class EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>EqualsAndHashCodePartialTwo</fold> that = <fold text='' expand='false'>(EqualsAndHashCodePartialTwo) </fold>o;
                return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Equals p' expand='false'>p</fold>ublic class EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>EqualsFull</fold> that = <fold text='' expand='false'>(EqualsFull) </fold>o;
            return ok == that.ok && (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null);
        }</fold></fold>

        <fold text='@Equalsˣ p' expand='false'>p</fold>ublic class EqualsPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()<fold text='' expand='false'></fold>)</fold> return false;
                <fold text='val' expand='false'>EqualsPartial</fold> that = <fold text='' expand='false'>(EqualsPartial) </fold>o;
                return <fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>that.data<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>that.data == null;
            }</fold></fold>
        }</fold>

        <fold text='@Equalsˣ p' expand='false'>p</fold>ublic class EqualsPartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
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

        </fold><fold text='' expand='false'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> result = (<fold text='' expand='false'>data != null ? </fold>data<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0);
            result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
            return result;
        }</fold></fold>

        <fold text='@HashCodeˣ p' expand='false'>p</fold>ublic class HashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data != null ? data.hashCode() : 0<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@HashCodeˣ p' expand='false'>p</fold>ublic class HashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
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
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>!(o instanceof DataFull)<fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>DataFull</fold> dataFull = <fold text='' expand='false'>(DataFull) </fold>o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>@Override
        public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }</fold></fold>

        <fold text='@Data p' expand='false'>p</fold>ublic class DataWithoutToString <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

        }</fold>

        <fold text='@Setter @ToString @EqualsAndHashCode p' expand='false'>p</fold>ublic class DataWithPartialGetters <fold text='{...}' expand='true'>{
            <fold text='@Getter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
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
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof LombokTestData.DataFull)<fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>LombokTestData.DataFull</fold> dataFull = <fold text='' expand='false'>(LombokTestData.DataFull) </fold>o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).<fold text='equals' expand='false'>isEquals()</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
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
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@Getter c' expand='false'>c</fold>lass FoldOnClass <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        @SuppressWarnings("ALL")
        <fold text='@Getter c' expand='false'>c</fold>lass FoldOnWithAnnotation <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    public class DirtyLombokGetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>dirty2<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public boolean isDirty2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text=' ' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
        </fold>}</fold>

        <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Getter p' expand='false'>p</fold>rivate boolean ok;

            public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>!dirty<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof DirtyData)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> return false;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> return false;

                return true;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty2<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    public class DirtyLombokSetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.dirty2 = dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void setDirty2(boolean dirty2)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.dirty = dirty2<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>

        <fold text='@Getter @EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Setter p' expand='false'>p</fold>rivate boolean ok;

            public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.dirty = !dirty<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>dirty<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof DirtyData)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>DirtyData</fold> dirtyData = <fold text='' expand='false'>(DirtyData) </fold>o;

                if <fold text='' expand='false'>(</fold>dirty != dirtyData.dirty<fold text='' expand='false'>)</fold> return false;
                if <fold text='' expand='false'>(</fold>ok != dirtyData.ok<fold text='' expand='false'>)</fold> return false;

                return true;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = (dirty ? 1 : 0);
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>ok ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            <fold text='@Setter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
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
            </fold><fold text='const' expand='false'><fold text='' expand='false'>public static final</fold> Logger logger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log(xlogger) p' expand='false'>p</fold>ublic class LogCustomNameDeprecated <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='const' expand='false'><fold text='' expand='false'>@Deprecated
            static final</fold> Logger xlogger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log @Log(log2) @Log(log3) p' expand='false'>p</fold>ublic class TripleLogJava <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
            </fold><fold text='const' expand='false'><fold text='' expand='false'>public static final</fold> Logger log2 = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
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
        public class NoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperBefore() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>// comment<fold text=' ' expand='true'>
                </fold>super()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
            </fold>}</fold>
        }</fold>
        public class NoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperAfter() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>super()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
                </fold>// comment<fold text=' ' expand='true'>
            </fold>}</fold>
        }</fold>
        <fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic class NoArgsConstructorSuper <fold text='{...}' expand='true'>{
            private String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorSuper()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>super()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        public class NoArgsConstructorSuperParent extends Parent <fold text='{...}' expand='true'>{
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
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new StaticNameArgs(field1, field2, field3)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'>
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
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new StaticNameArgs(field1, field2, field3)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'>
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
            </fold><fold text='' expand='false'>public ValueArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                this.field3 = <fold text='<<' expand='false'>field3</fold>;
            }<fold text='' expand='false'></fold></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>o == null || <fold text='class' expand='false'>getClass()</fold> != o.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
                <fold text='val' expand='false'>ValueArgs</fold> valueArgs = <fold text='' expand='false'>(ValueArgs) </fold>o;
                if <fold text='' expand='false'>(</fold>field2 != valueArgs.field2<fold text='' expand='false'>)</fold> return false;
                if <fold text='' expand='false'>(</fold>field3 != valueArgs.field3<fold text='' expand='false'>)</fold> return false;
                return <fold text='' expand='false'>field1 != null ? </fold>field1<fold text='?.' expand='false'><fold text=' ≡ ' expand='false'>.</fold>equals(</fold>valueArgs.field1<fold text='?)' expand='false'>)</fold><fold text=' ?: ' expand='false'> : </fold>valueArgs.field1 == null;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                <fold text='var' expand='false'>int</fold> result = <fold text='' expand='false'>field1 != null ? </fold>field1<fold text='?.' expand='false'>.</fold>hashCode()<fold text=' ?: ' expand='false'> : </fold>0;
                result = 31 * result + field2;
                result = 31 * result<fold text=' + ' expand='false'> + (</fold>field3 ? 1 : 0<fold text='' expand='false'>)</fold>;
                return result;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
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
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field2<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>@Override
            public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof ValueArgsSuper)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>ValueArgsSuper</fold> that = <fold text='' expand='false'>(ValueArgsSuper) </fold>o;
                return field2 == that.field2 && field3 == that.field3 && <fold text='' expand='false'>Objects.equals(</fold>field1<fold text=' ≡ ' expand='false'>, </fold>that.field1<fold text='' expand='false'>)</fold>;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
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
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field1<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field2<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field3<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>
    class SingleField <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic static class AllArgs <fold text='{...}' expand='true'>{
            private String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgs(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic static class ReqArgs <fold text='{...}' expand='true'>{
            private final String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ReqArgs(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@Value p' expand='false'>p</fold>ublic static class Value <fold text='{...}' expand='true'>{
            private final String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public Value(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field1<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>@Override
            public <fold text='' expand='false'>final</fold> boolean equals(Object o) <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>this == o<fold text='' expand='false'>)</fold> return true;
                if <fold text='' expand='false'>(</fold>!(o instanceof Value)<fold text='' expand='false'>)</fold> return false;

                <fold text='val' expand='false'>Value</fold> value = <fold text='' expand='false'>(Value) </fold>o;
                return <fold text='' expand='false'>Objects.equals(</fold>field1<fold text=' ≡ ' expand='false'>, </fold>value.field1<fold text='' expand='false'>)</fold>;
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Objects.hashCode(field1)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        <fold text='@LightValue p' expand='false'>p</fold>ublic static class ValueWithoutEqualsAndHashCode <fold text='{...}' expand='true'>{
            private final String field1;<fold text='' expand='false'>
            <fold text='' expand='false'></fold>public ValueWithoutEqualsAndHashCode(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>field1<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
        class Modifers <fold text='{...}' expand='true'>{
            <fold text='@AllArgsConstructor(default) s' expand='false'>s</fold>tatic class AllArgsDefault <fold text='{...}' expand='true'>{
                private String field1;<fold text='' expand='false'>
                </fold><fold text='' expand='false'>AllArgsDefault(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
            <fold text='@AllArgsConstructor(private) s' expand='false'>s</fold>tatic class AllArgsPrivate <fold text='{...}' expand='true'>{
                private String field1;<fold text='' expand='false'>
                </fold><fold text='' expand='false'>private AllArgsPrivate(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
            <fold text='@AllArgsConstructor(protected) s' expand='false'>s</fold>tatic class AllArgsProteced <fold text='{...}' expand='true'>{
                private String field1;<fold text='' expand='false'>
                </fold><fold text='' expand='false'>protected AllArgsProteced(String field1)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.field1 = field1<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>
        }</fold>
    }</fold>


    <fold text='@Builder c' expand='false'>c</fold>lass ClassWithBuilder <fold text='{...}' expand='true'>{
        private String name;

        class ClassWithBuilderBuilder <fold text='{...}' expand='true'>{
            private String name;

            public ClassWithBuilderBuilder name(String name) <fold text='{...}' expand='true'>{
                this.name = <fold text='<<' expand='false'>name</fold>;
                return this;
            }</fold>

            public ClassWithBuilder build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new ClassWithBuilder()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

}
