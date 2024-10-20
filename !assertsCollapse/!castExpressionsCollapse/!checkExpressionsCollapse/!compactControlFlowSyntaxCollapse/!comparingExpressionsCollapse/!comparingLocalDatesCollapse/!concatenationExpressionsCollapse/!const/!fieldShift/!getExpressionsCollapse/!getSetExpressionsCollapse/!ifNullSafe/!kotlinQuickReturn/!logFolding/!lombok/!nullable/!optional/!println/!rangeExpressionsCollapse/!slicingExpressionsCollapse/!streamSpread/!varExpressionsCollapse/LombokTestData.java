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
public class LombokTestData {

    private static final long serialVersionUID = 1234567L;

    LombokTestData data;
    boolean ok;
    String string;

    public LombokTestData getData()<fold text=' { ' expand='false'> {
        </fold>return data;<fold text=' }' expand='false'>
    }</fold>

    public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
        </fold>this.data = data;<fold text=' }' expand='false'>
    }</fold>

    public boolean isOk()<fold text=' { ' expand='false'> {
        </fold>return ok;<fold text=' }' expand='false'>
    }</fold>

    public void setOk(boolean ok)<fold text=' { ' expand='false'> {
        </fold>this.ok = ok;<fold text=' }' expand='false'>
    }</fold>

    public String getString()<fold text=' { ' expand='false'> {
        </fold>return string;<fold text=' }' expand='false'>
    }</fold>

    public void setString(String string)<fold text=' { ' expand='false'> {
        </fold>this.string = string;<fold text=' }' expand='false'>
    }</fold>

    public Optional<LombokTestData> asOptional()<fold text=' { ' expand='false'> {
        </fold>return Optional.ofNullable(data);<fold text=' }' expand='false'>
    }</fold>

    public class LombokGetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>

        public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold>

        public class LombokGettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class LombokSetters <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>

        public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold>

        public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold>

        public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold>

        public class LombokSettersPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        public class LombokSettersFinalField <fold text='{...}' expand='true'>{
            LombokTestData data;
            final boolean ok = true;

            public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class ToStringFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        @Override
        public String toString() <fold text='{...}' expand='true'>{
            return "ToStringFull{"<fold text=' + ' expand='false'> +
                    </fold>"data=" + data<fold text=' + ' expand='false'> +
                    </fold>", ok=" + ok<fold text=' + ' expand='false'> +
                    </fold>'}';
        }</fold>

        public class ToStringPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            @Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{"<fold text=' + ' expand='false'> +
                        </fold>"data=" + data<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold>
        }</fold>

        public class ToStringPartial2 <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ToStringPartial{"<fold text=' + ' expand='false'> +
                        </fold>"data=" + data<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold>
        }</fold>
    }</fold>

    public class EqualsAndHashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }</fold>

        @Override
        public int hashCode() <fold text='{...}' expand='true'>{
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }</fold>

        public class EqualsAndHashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }</fold>

            @Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return data != null ? data.hashCode() : 0;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        public class EqualsAndHashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }</fold>

            @Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold>
        }</fold>
    }</fold>

    public class EqualsFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }</fold>

        public class EqualsPartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }</fold>
        }</fold>

        public class EqualsPartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }</fold>
        }</fold>
    }</fold>

    public class HashCodeFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        @Override
        public int hashCode() <fold text='{...}' expand='true'>{
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }</fold>

        public class HashCodePartial <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            @Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return data != null ? data.hashCode() : 0;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        public class HashCodePartialTwo <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold>
        }</fold>
    }</fold>

    public class DataFull <fold text='{...}' expand='true'>{
        LombokTestData data;
        boolean ok;

        public LombokTestData getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>

        public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold>

        public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold>

        public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold>

        @Override
        public boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }</fold>

        @Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
        }</fold>

        @Override
        public String toString() <fold text='{...}' expand='true'>{
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }</fold>

        public class DataWithoutToString <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold>

            public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold>

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>

            public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold>

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold>

            @Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold>

        }</fold>

        public class DataWithPartialGetters <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold>

            public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold>

            public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold>

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold>

            @Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold>

            @Override
            public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold>
        }</fold>

        public class DataWithPartialSetters <fold text='{...}' expand='true'>{
            LombokTestData data;
            boolean ok;

            public LombokTestData getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
            }</fold>

            public void setData(LombokTestData data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
            }</fold>

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }</fold>

            @Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();<fold text=' }' expand='false'>
            }</fold>

            @Override
            public String toString() <fold text='{...}' expand='true'>{
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }</fold>
        }</fold>
    }</fold>

    public class FoldOn <fold text='{...}' expand='true'>{
        public class FoldOnPublic <fold text='{...}' expand='true'>{
            boolean ok;

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        class FoldOnClass <fold text='{...}' expand='true'>{
            boolean ok;

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        @SuppressWarnings("ALL")
        class FoldOnWithAnnotation <fold text='{...}' expand='true'>{
            boolean ok;

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class DirtyLombokGetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty()<fold text=' { ' expand='false'> {
            </fold>return dirty2;<fold text=' }' expand='false'>
        }</fold>

        public boolean isDirty2()<fold text=' { ' expand='false'> {
            </fold>return dirty;<fold text=' }' expand='false'>
        }</fold>

        public class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            private boolean ok;

            public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return !dirty;<fold text=' }' expand='false'>
            }</fold>

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }</fold>

            @Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            boolean ok;

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>

            public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return dirty2;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class DirtyLombokSetters <fold text='{...}' expand='true'>{
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
            </fold>this.dirty2 = dirty;<fold text=' }' expand='false'>
        }</fold>

        public void setDirty2(boolean dirty2)<fold text=' { ' expand='false'> {
            </fold>this.dirty = dirty2;<fold text=' }' expand='false'>
        }</fold>

        public class DirtyData <fold text='{...}' expand='true'>{
            boolean dirty;
            private boolean ok;

            public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
                </fold>this.dirty = !dirty;<fold text=' }' expand='false'>
            }</fold>

            public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold>

            public boolean isDirty()<fold text=' { ' expand='false'> {
                </fold>return dirty;<fold text=' }' expand='false'>
            }</fold>

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>

            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }</fold>

            @Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }</fold>
        }</fold>

        public class DirtySingle <fold text='{...}' expand='true'>{
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
            }</fold>

            public void setDirty(boolean dirty)<fold text=' { ' expand='false'> {
                </fold>this.ok = dirty;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class LogAnnotation <fold text='{...}' expand='true'>{
        public class LogJava <fold text='{...}' expand='true'>{
            Logger log = Logger.getLogger("LogAnnotation.class");
        }</fold>

        public class LogJava2 <fold text='{...}' expand='true'>{
            Logger log = Logger.getLogger("LogAnnotation.class");
        }</fold>

        public class LogDiffrentFieldName <fold text='{...}' expand='true'>{
            public static final Logger logger = Logger.getLogger("LogAnnotation.class");
        }</fold>

        public class LogCustomNameDeprecated <fold text='{...}' expand='true'>{
            @Deprecated
            static final Logger xlogger = Logger.getLogger("LogAnnotation.class");
        }</fold>

        public class TripleLogJava <fold text='{...}' expand='true'>{
            Logger log = Logger.getLogger("LogAnnotation.class");
            public static final Logger log2 = Logger.getLogger("LogAnnotation.class");
            static Logger log3 = Logger.getLogger("LogAnnotation.class");
        }</fold>
    }</fold>


    public class Parent <fold text='{...}' expand='true'>{
        public Parent(String child) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public class NoArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        public class NoArgsConstructor <fold text='{...}' expand='true'>{
            public NoArgsConstructor() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>
        class NoArgsConstructorPrivate <fold text='{...}' expand='true'>{
            private NoArgsConstructorPrivate() <fold text='{}' expand='true'>{
            }</fold>
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

        public class ProtectedNoArgsConstructorSuperAfter <fold text='{...}' expand='true'>{
            protected ProtectedNoArgsConstructorSuperAfter() <fold text='{}' expand='true'>{
                // comment
            }</fold>
        }</fold>
        public class NoArgsConstructorSuper <fold text='{...}' expand='true'>{
            private String field1;
            public NoArgsConstructorSuper()<fold text=' { ' expand='false'> {
                </fold>super();<fold text=' }' expand='false'>
            }</fold>
        }</fold>
        public class NoArgsConstructorSuperParent extends Parent <fold text='{...}' expand='true'>{
            public NoArgsConstructorSuperParent()<fold text=' { ' expand='false'> {
                </fold>super(null);<fold text=' }' expand='false'>
            }</fold>
        }</fold>

    }</fold>

    public class AllArgsConstructorAnnotation <fold text='{...}' expand='true'>{
        public static class AllArgs <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
        }</fold>
        public static class AllArgsBrokenFieldAssigmentLeft <fold text='{...}' expand='true'>{
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentLeft(int field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field1;
                this.field3 = field3;
            }</fold>
        }</fold>
        public static class AllArgsBrokenFieldAssigmentRight <fold text='{...}' expand='true'>{
            private int field1;
            private int field2;
            private boolean field3;
            public AllArgsBrokenFieldAssigmentRight(int field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field1 = field2;
                this.field3 = field3;
            }</fold>
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

        public static class AllArgsSuper <fold text='{...}' expand='true'>{
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
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
        public static class RequiredArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
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

        public static class RequiredArgsSuper <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
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
        public static class ValueArgs <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgs(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
            public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold>
            public int getField2()<fold text=' { ' expand='false'> {
                </fold>return field2;<fold text=' }' expand='false'>
            }</fold>
            public boolean isField3()<fold text=' { ' expand='false'> {
                </fold>return field3;<fold text=' }' expand='false'>
            }</fold>
            @Override
            public boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ValueArgs valueArgs = (ValueArgs) o;
                if (field2 != valueArgs.field2) return false;
                if (field3 != valueArgs.field3) return false;
                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;
            }</fold>
            @Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = field1 != null ? field1.hashCode() : 0;
                result = 31 * result + field2;
                result = 31 * result + (field3 ? 1 : 0);
                return result;
            }</fold>

            @Override
            public String toString() <fold text='{...}' expand='true'>{
                return "ValueArgs{"<fold text=' + ' expand='false'> +
                        </fold>"field1='" + field1 + '\''<fold text=' + ' expand='false'> +
                        </fold>", field2=" + field2<fold text=' + ' expand='false'> +
                        </fold>", field3=" + field3<fold text=' + ' expand='false'> +
                        </fold>'}';
            }</fold>
        }</fold>
        public static class ValueArgsSuper <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgsSuper(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
            public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold>
            public int getField2()<fold text=' { ' expand='false'> {
                </fold>return field2;<fold text=' }' expand='false'>
            }</fold>
            public boolean isField3()<fold text=' { ' expand='false'> {
                </fold>return field3;<fold text=' }' expand='false'>
            }</fold>
            @Override
            public final boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof ValueArgsSuper)) return false;

                ValueArgsSuper that = (ValueArgsSuper) o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }</fold>

            @Override
            public int hashCode() <fold text='{...}' expand='true'>{
                int result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }</fold>
        }</fold>
        public static class ValueWihhoutEqualsAndHashcode <fold text='{...}' expand='true'>{
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
            public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold>
            public int getField2()<fold text=' { ' expand='false'> {
                </fold>return field2;<fold text=' }' expand='false'>
            }</fold>
            public boolean isField3()<fold text=' { ' expand='false'> {
                </fold>return field3;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>
    class SingleField <fold text='{...}' expand='true'>{
        public static class AllArgs <fold text='{...}' expand='true'>{
            private String field1;
            public AllArgs(String field1)<fold text=' { ' expand='false'> {
                </fold>this.field1 = field1;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
        public static class ReqArgs <fold text='{...}' expand='true'>{
            private final String field1;
            public ReqArgs(String field1)<fold text=' { ' expand='false'> {
                </fold>this.field1 = field1;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
        public static class Value <fold text='{...}' expand='true'>{
            private final String field1;
            public Value(String field1)<fold text=' { ' expand='false'> {
                </fold>this.field1 = field1;<fold text=' }' expand='false'>
            }</fold>
            public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold>
            @Override
            public final boolean equals(Object o) <fold text='{...}' expand='true'>{
                if (this == o) return true;
                if (!(o instanceof Value)) return false;

                Value value = (Value) o;
                return Objects.equals(field1, value.field1);
            }</fold>
            @Override
            public int hashCode()<fold text=' { ' expand='false'> {
                </fold>return Objects.hashCode(field1);<fold text=' }' expand='false'>
            }</fold>
        }</fold>
        public static class ValueWithoutEqualsAndHashCode <fold text='{...}' expand='true'>{
            private final String field1;
            public ValueWithoutEqualsAndHashCode(String field1)<fold text=' { ' expand='false'> {
                </fold>this.field1 = field1;<fold text=' }' expand='false'>
            }</fold>
            public String getField1()<fold text=' { ' expand='false'> {
                </fold>return field1;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
        class Modifers <fold text='{...}' expand='true'>{
            static class AllArgsDefault <fold text='{...}' expand='true'>{
                private String field1;
                AllArgsDefault(String field1)<fold text=' { ' expand='false'> {
                    </fold>this.field1 = field1;<fold text=' }' expand='false'>
                }</fold>
            }</fold>
            static class AllArgsPrivate <fold text='{...}' expand='true'>{
                private String field1;
                private AllArgsPrivate(String field1)<fold text=' { ' expand='false'> {
                    </fold>this.field1 = field1;<fold text=' }' expand='false'>
                }</fold>
            }</fold>
            static class AllArgsProteced <fold text='{...}' expand='true'>{
                private String field1;
                protected AllArgsProteced(String field1)<fold text=' { ' expand='false'> {
                    </fold>this.field1 = field1;<fold text=' }' expand='false'>
                }</fold>
            }</fold>
        }</fold>
    }</fold>

    class FieldLevelData <fold text='{...}' expand='true'>{
        private String name;
        private String ignored;

        public String getName()<fold text=' { ' expand='false'> {
            </fold>return name;<fold text=' }' expand='false'>
        }</fold>

        public void setName(String name)<fold text=' { ' expand='false'> {
            </fold>this.name = name;<fold text=' }' expand='false'>
        }</fold>

        @Override
        public final boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }</fold>

        @Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return Objects.hashCode(name);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class FieldLevelValue <fold text='{...}' expand='true'>{
        private final String name = "1";
        private String ignored;

        public String getName()<fold text=' { ' expand='false'> {
            </fold>return name;<fold text=' }' expand='false'>
        }</fold>

        @Override
        public final boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }</fold>

        @Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return Objects.hashCode(name);<fold text=' }' expand='false'>
        }</fold>
    }</fold>
    class FieldLevelNotFinalNotValue <fold text='{...}' expand='true'>{
        private String name;
        private String ignored;

        public String getName()<fold text=' { ' expand='false'> {
            </fold>return name;<fold text=' }' expand='false'>
        }</fold>

        @Override
        public final boolean equals(Object o) <fold text='{...}' expand='true'>{
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }</fold>

        @Override
        public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return Objects.hashCode(name);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class ClassWithBuilder <fold text='{...}' expand='true'>{
        private String name;
        class ClassWithBuilderBuilder <fold text='{...}' expand='true'>{
            private String name;
            public ClassWithBuilderBuilder name(String name) <fold text='{...}' expand='true'>{
                this.name = name;
                return this;
            }</fold>
            public ClassWithBuilder build()<fold text=' { ' expand='false'> {
                </fold>return new ClassWithBuilder();<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    class Constructors <fold text='{...}' expand='true'>{
        public class FiveConstructors <fold text='{...}' expand='true'>{
            private int field1;
            private String field2;
            private double field3;
            private boolean field4;
            public FiveConstructors() <fold text='{}' expand='true'>{
            }</fold>
            public FiveConstructors(int field1)<fold text=' { ' expand='false'> {
                </fold>this.field1 = field1;<fold text=' }' expand='false'>
            }</fold>
            public FiveConstructors(int field1, String field2) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
            }</fold>
            public FiveConstructors(int field1, String field2, double field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
            public FiveConstructors(int field1, String field2, double field3, boolean field4) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }</fold>
        }</fold>

        public class FiveConstructorsMod <fold text='{...}' expand='true'>{
            private int field1;
            private String field2;
            private double field3;
            private boolean field4;
            public FiveConstructorsMod() <fold text='{}' expand='true'>{
            }</fold>
            private FiveConstructorsMod(int field1)<fold text=' { ' expand='false'> {
                </fold>this.field1 = field1;<fold text=' }' expand='false'>
            }</fold>
            FiveConstructorsMod(int field1, String field2) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
            }</fold>
            protected FiveConstructorsMod(int field1, String field2, double field3) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }</fold>
            public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) <fold text='{...}' expand='true'>{
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }</fold>
        }</fold>
    }</fold>

}
