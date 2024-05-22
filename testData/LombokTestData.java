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
 * {@link com.intellij.advancedExpressionFolding.extension.LombokExt#addLombokSupport(com.intellij.psi.PsiClass)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testLombokTestData()}
 */</fold>
@SuppressWarnings("ALL")
<fold text='@Getter @Setter @Serial p' expand='false'>p</fold>ublic class LombokTestData {<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private static final long serialVersionUID = 1234567L;</fold>

    LombokTestData data;
    boolean ok;
    String string;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = data;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = ok;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = string;<fold text=' }' expand='false'>
    }</fold></fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data);<fold text=' }' expand='false'>
    }</fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic class LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold>

        public class LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold></fold>

        public class LombokSettersPartial <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        public class LombokSettersFinalField <fold text='{...}' expand='true'>{
            <fold text='@Setter L' expand='false'>L</fold>ombokTestData data;
            final boolean ok = true;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@ToString p' expand='false'>p</fold>ublic class ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        <fold text='' expand='false'></fold>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return "ToStringFull{"<fold text=' + ' expand='false'> +
                    </fold>"data=" + data<fold text=' + ' expand='false'> +
                    </fold>", ok=" + ok<fold text=' + ' expand='false'> +
                    </fold>'}';
        }</fold></fold>

        <fold text='@ToStringˣ p' expand='false'>p</fold>ublic class ToStringPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{"<fold text=' + ' expand='false'> +
                        </fold>"data=" + data<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold></fold>
        }</fold>

        <fold text='@ToStringˣ p' expand='false'>p</fold>ublic class ToStringPartial2 <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{"<fold text=' + ' expand='false'> +
                        </fold>"data=" + data<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }</fold></fold>

        <fold text='@EqualsAndHashCodeˣ p' expand='false'>p</fold>ublic class EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return data != null ? data.hashCode() : 0;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@EqualsAndHashCodeˣ p' expand='false'>p</fold>ublic class EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Equals p' expand='false'>p</fold>ublic class EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }</fold></fold>

        <fold text='@Equalsˣ p' expand='false'>p</fold>ublic class EqualsPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }</fold></fold>
        }</fold>

        <fold text='@Equalsˣ p' expand='false'>p</fold>ublic class EqualsPartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@HashCode p' expand='false'>p</fold>ublic class HashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public int hashCode() <fold text='{...}' expand='true'>{
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }</fold></fold>

        <fold text='@HashCodeˣ p' expand='false'>p</fold>ublic class HashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return data != null ? data.hashCode() : 0;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@HashCodeˣ p' expand='false'>p</fold>ublic class HashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Data p' expand='false'>p</fold>ublic class DataFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>@Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

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

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold>

        }</fold>

        <fold text='@Setter @ToString @EqualsAndHashCode p' expand='false'>p</fold>ublic class DataWithPartialGetters <fold text='{...}' expand='true'>{
            <fold text='@Getter L' expand='false'>L</fold>ombokTestData data;
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

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

            </fold><fold text='' expand='false'>public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

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

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        <fold text='@Getter c' expand='false'>c</fold>lass FoldOnClass <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>

        @SuppressWarnings("ALL")
        <fold text='@Getter c' expand='false'>c</fold>lass FoldOnWithAnnotation <fold text='{...}' expand='true'>{
            boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    public class DirtyLombokGetters <fold text='{...}' expand='true'>{
        <fold text='@Getter(dirty) b' expand='false'>b</fold>oolean dirty;
        <fold text='@Getter(dirty) p' expand='false'>p</fold>rivate boolean dirty2;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {
            </fold>return dirty2;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isDirty2()<fold text=' { ' expand='false'> {
            </fold>return dirty;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            <fold text='@Getter(dirty) b' expand='false'>b</fold>oolean dirty;
            <fold text='@Getter p' expand='false'>p</fold>rivate boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return !dirty;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            <fold text='@Getter(dirty) b' expand='false'>b</fold>oolean dirty;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return dirty2;<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
    }</fold>

    public class DirtyLombokSetters <fold text='{...}' expand='true'>{
        <fold text='@Setter(dirty) b' expand='false'>b</fold>oolean dirty;
        <fold text='@Setter(dirty) p' expand='false'>p</fold>rivate boolean dirty2;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
            </fold>this.dirty2 = dirty;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setDirty2(boolean dirty2)<fold text=' { ' expand='false'> {
            </fold>this.dirty = dirty2;<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Getter @EqualsAndHashCode p' expand='false'>p</fold>ublic class DirtyData <fold text='{...}' expand='true'>{
            <fold text='@Setter(dirty) b' expand='false'>b</fold>oolean dirty;
            <fold text='@Setter p' expand='false'>p</fold>rivate boolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
                </fold>this.dirty = !dirty;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return dirty;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold></fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            <fold text='@Setter(dirty) b' expand='false'>b</fold>oolean dirty;
            <fold text='@Setter b' expand='false'>b</fold>oolean ok;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
                </fold>this.ok = dirty;<fold text=' }' expand='false'>
            }</fold></fold>
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
            </fold><fold text='' expand='false'>public static final Logger logger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log(xlogger) p' expand='false'>p</fold>ublic class LogCustomNameDeprecated <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>@Deprecated
            static final Logger xlogger = Logger.getLogger("LogAnnotation.class");</fold>
        }</fold>

        <fold text='@Log @Log(log2) @Log(log3) p' expand='false'>p</fold>ublic class TripleLogJava <fold text='{...}' expand='true'>{<fold text='' expand='false'>
            </fold><fold text='' expand='false'>Logger log = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public static final Logger log2 = Logger.getLogger("LogAnnotation.class");</fold><fold text='' expand='false'>
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
        public class NoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperBefore() <fold text='{...}' expand='true'>{
                // comment
                super();
            }</fold>
        }</fold>
        public class NoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperAfter() <fold text='{...}' expand='true'>{
                super();
                // comment
            }</fold>
        }</fold>
        <fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic class NoArgsConstructorSuper <fold text='{...}' expand='true'>{
            private String field1;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public NoArgsConstructorSuper()<fold text=' { ' expand='false'> {
                </fold>super();<fold text=' }' expand='false'>
            }</fold></fold>
        }</fold>
        public class NoArgsConstructorSuperParent extends Parent <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperParent()<fold text=' { ' expand='false'> {
                </fold>super(null);<fold text=' }' expand='false'>
            }</fold>
        }</fold>

    }</fold>

    public class AllArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic static class AllArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold></fold>
        }</fold>
        public static class AllArgsNoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;

            public AllArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                // comment
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
        }</fold>
        public static class AllArgsNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;

            public AllArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                // comment
            }</fold>
        }</fold>

        <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic static class AllArgsSuper <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public AllArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold></fold>
        }</fold>

        public static class StaticNameArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;
            private StaticNameArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
            public static StaticNameArgs of(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                return new StaticNameArgs(field1, field2, field3);
            }</fold>
        }</fold>

        public static class ProtectedArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;
            protected ProtectedArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
        }</fold>
    }</fold>


    public class RequiredArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic static class RequiredArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public RequiredArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold></fold>
        }</fold>

        public static class RequiredArgsNoArgsConstructorSuperBefore <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperBefore(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                // comment
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
        }</fold>

        public static class RequiredArgsNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsNoArgsConstructorSuperAfter(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                // comment
            }</fold>
        }</fold>

        <fold text='@RequiredArgsConstructor p' expand='false'>p</fold>ublic static class RequiredArgsSuper <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public RequiredArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold></fold>
        }</fold>

        public static class StaticNameArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            private StaticNameArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>

            public static StaticNameArgs of(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                return new StaticNameArgs(field1, field2, field3);
            }</fold>
        }</fold>

        public static class ProtectedArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            protected ProtectedArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
        }</fold>
    }</fold>

    public class ValueAnnotation <fold text='{...}' expand='true'>{
        <fold text='@Value p' expand='false'>p</fold>ublic static class ValueArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {
                </fold>return field2;<fold text=' }' expand='false'>
            }</fold><fold text='' expand='false'></fold>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {
                </fold>return field3;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>@Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ValueArgs valueArgs = (ValueArgs) o;
                if (field2 != valueArgs.field2) return false;
                if (field3 != valueArgs.field3) return false;
                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = field1 != null ? field1.hashCode() : 0;
                result = 31 * result + field2;
                result = 31 * result + (field3 ? 1 : 0);
                return result;
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ValueArgs{"<fold text=' + ' expand='false'> +
                        </fold>"field1='" + field1 + '\''<fold text=' + ' expand='false'> +
                        </fold>", field2=" + field2<fold text=' + ' expand='false'> +
                        </fold>", field3=" + field3<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold></fold>
        }</fold>
        <fold text='@Value p' expand='false'>p</fold>ublic static class ValueArgsSuper <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>public ValueArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public int getField2()<fold text=' { ' expand='false'> {
                </fold>return field2;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>public boolean isField3()<fold text=' { ' expand='false'> {
                </fold>return field3;<fold text=' }' expand='false'>
            }</fold></fold><fold text='' expand='false'>
            </fold><fold text='' expand='false'>@Override
            public final boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof ValueArgsSuper)) return false;

                ValueArgsSuper that = (ValueArgsSuper) o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>@Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }</fold></fold>
        }</fold>
    }</fold>


}
